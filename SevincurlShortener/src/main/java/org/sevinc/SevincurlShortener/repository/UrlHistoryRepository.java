package org.sevinc.SevincurlShortener.repository;

import org.sevinc.SevincurlShortener.entity.UrlHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlHistoryRepository extends JpaRepository<UrlHistory, Integer> {
    List<UrlHistory> findAllById(int id);
    List<UrlHistory> findAllByUrlId(int urlid);
}
