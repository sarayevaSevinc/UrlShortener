package org.sevinc.SevincurlShortener.services;

import org.sevinc.SevincurlShortener.entity.ForgotPasswordUrl;
import org.sevinc.SevincurlShortener.repository.PasswordUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
 public class PasswordUrlService {
    PasswordUrlRepository repository;

     public PasswordUrlService(PasswordUrlRepository repository) {
         this.repository = repository;
     }

     public void save(ForgotPasswordUrl passwordUrl){
         this.repository.save(passwordUrl);

    }
    public Optional<ForgotPasswordUrl> getByPasswordUrl(String url){
         return repository.findByPasswordUrl(url);
    }
 }
