package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.UrlHistory;
import org.sevinc.SevincurlShortener.repository.UrlHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UrlHistoryService {
     UrlHistoryRepository repository;

    public UrlHistoryService(UrlHistoryRepository urlHistoryRepository) {
        this.repository = urlHistoryRepository;
    }

    public void add(UrlHistory urlHistory){
        this.repository.save(urlHistory);
    }
    public List<UrlHistory> getAllByUrlId(int id){
        return this.repository.findAllByUrlId(id);
    }
    public  List<UrlHistory> getAllByUrlIdAndUserId(int urlId, int userId)
    {    log.info(getAllByUrlId(urlId).toString());
        return getAllByUrlId(urlId).stream().filter(x-> x.getPerson().getId() == userId).collect(Collectors.toList());
    }}
