package org.sevinc.SevincurlShortener.repository;


import org.sevinc.SevincurlShortener.entity.db.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

    List<Url> findAllByUserId(int id);

    Optional<Url> findById(int id);

    Optional<Url> findByShortUrl(String shortUrl);

    int countAllByShortUrl(String shortUrl);
}
