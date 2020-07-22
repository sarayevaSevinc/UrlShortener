package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.db.Url;
import org.sevinc.SevincurlShortener.repository.CacheRepository;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Log4j2
@Service
public class CacheService {
    private final CacheRepository cacheRepository;
    private final Utilities utilities;

    public CacheService(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
        utilities = new Utilities();
    }

    public void add(Url url) {
        if (cacheRepository.getSizeOfCache() == 20000)
            cacheRepository.remove(cacheRepository.getFirstInUrlId());
        cacheRepository.add(url);
    }

    public Optional<Url> getUrlByShortUrl(String shortUrl) {
        log.info("I am in the CacheService...");
        return cacheRepository.searchWithShortUrl(shortUrl);
    }

    public void updateUrl(Url url) {
        cacheRepository.update(url);
    }

    public HashMap<Integer, Url> getAll() {
        return cacheRepository.getAll();
    }

    public void deleteExpiredUrls() {
        for (Url url : cacheRepository.getAll().values()) {
            if (utilities.parseExpirationDate(url.getExpiresAt()).isAfter(LocalDateTime.now().plusMonths(1)))
                cacheRepository.remove(url.getId());
        }
    }
}
