package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.entity.db.Url;
import org.sevinc.SevincurlShortener.entity.db.UrlHistory;
import org.sevinc.SevincurlShortener.repository.UrlHistoryRepository;
import org.sevinc.SevincurlShortener.repository.UrlRepository;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Log4j2
@Service
@Component
public class UrlService {
     private HashMap <Person, Url> cache = new HashMap<>();
    private UrlRepository repository;
    private Utilities utilities;
    private UrlHistoryRepository urlHistoryRepository;
    private CacheService cacheService;

    public UrlService(UrlRepository repository, UrlHistoryRepository urlHistoryRepository, CacheService cacheService) {
        this.repository = repository;
        utilities = new Utilities();
        this.urlHistoryRepository = urlHistoryRepository;
        this.cacheService =cacheService;
    }

    public void save(Url url) {
        this.repository.save(url);
    }

    public void createAndSaveNewUrl(String longUrl, String exDate, PersonDetails personDetails) {
        if (utilities.isValid(longUrl)) {

            String shortUrl = utilities.getShortUrl();

            while (repository.countAllByShortUrl(shortUrl) > 0) {
                shortUrl = utilities.getShortUrl();
            }
            Url newUrl = new Url(longUrl, shortUrl,
                    utilities.getDate(),
                    utilities.mapperPersonDetailsToUser(personDetails),
                    utilities.getExpirationDate(exDate), Short.valueOf("1"));
            save(newUrl);
            cacheService.add(newUrl);
        }
    }


    public List<Url> getAllByUserId(int id) {

        return this.repository.findAllByUserId(id);
    }

    public void updateEnabled(int id, boolean enabled, PersonDetails personDetails) {
        Optional<Url> optionalUrl = this.repository.findById(id);
        if (optionalUrl.isPresent() && optionalUrl.get().getUser().getId() == personDetails.getId()) {
            optionalUrl.get().setEnabled((short) (enabled ? 1 : 0));
            this.repository.save(optionalUrl.get());
            this.cacheService.updateUrl(optionalUrl.get());
        }
    }

    public void increaseVisitedCount(Url url) {
        url.setVisitedCount(url.getVisitedCount() + 1);
        this.repository.save(url);
        this.cacheService.updateUrl(url);
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
        return this.repository.findByShortUrl(shortUrl);
    }

    public String redirectUrl(String value, String address) {
        Optional<Url> optionalUrl = cacheService.getUrlByShortUrl(value);
        optionalUrl = optionalUrl.isPresent()? optionalUrl : findByShortUrl(value);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            if (isValidUrl(url)) {
                increaseVisitedCount(url);
                urlHistoryRepository.save(new UrlHistory(utilities.getDate(),
                        utilities.getTime(), address, url, url.getUser()));
                return url.getLongUrl();
            }
        }
        return "/exception";
    }

    private boolean isValidUrl(Url url) {
        LocalDateTime expirationDateTime = utilities.parseExpirationDate(url.getExpiresAt());
        if (expirationDateTime.isBefore(LocalDateTime.now())) {
            return false;
        }
        if (url.getEnabled() == 0) {
            return false;
        }
        return true;
    }

    @Scheduled(fixedRate = 87000000)
    public void deleteExpiredUrls(){
        List<Url> collect = repository.findAll().stream().filter(x -> utilities.parseExpirationDate(x.getExpiresAt()).isAfter(LocalDateTime.now().plusMonths(1))).collect(Collectors.toList());
             repository.saveAll(collect);
             cacheService.deleteExpiredUrls();
    }
}