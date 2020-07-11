package org.sevinc.SevincurlShortener.repository;

import org.sevinc.SevincurlShortener.entity.ForgotPasswordUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordUrlRepository extends JpaRepository<ForgotPasswordUrl, Integer> {
    Optional<ForgotPasswordUrl> findByPasswordUrl(String url);

}
