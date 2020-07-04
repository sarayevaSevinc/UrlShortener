package org.sevinc.SevincurlShortener.services;

import org.sevinc.SevincurlShortener.entity.UrlHistory;
import org.sevinc.SevincurlShortener.repository.UrlHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlHistoryService {
     UrlHistoryRepository repository;

    public UrlHistoryService(UrlHistoryRepository urlHistoryRepository) {
        this.repository = urlHistoryRepository;
    }

    public void add(UrlHistory urlHistory){
        this.repository.save(urlHistory);
    }
    public List<UrlHistory> getAllById(int id){
        return this.repository.findAllById(id);
    }
}
