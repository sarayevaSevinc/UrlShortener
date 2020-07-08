package org.sevinc.SevincurlShortener.repository;

import org.sevinc.SevincurlShortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
     Optional<Url> findAllByShortUrl(String shortUrl);
    List<Url> findAllByUserId (int id);
}
