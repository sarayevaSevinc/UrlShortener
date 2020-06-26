package org.sevinc.SevincurlShortener.Repository;

import org.sevinc.SevincurlShortener.Entity.Person;
import org.sevinc.SevincurlShortener.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

}
