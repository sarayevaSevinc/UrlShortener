package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.db.Url;
import org.sevinc.SevincurlShortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class UrlService {

     private UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }
    public void save(Url  url){
        this.repository.save(url);
    }
    public Optional <Url> searchUrl(String shortUrl){
       return this.repository.findAll().stream().filter(url-> url.getShortUrl().equals(shortUrl)).findAny();

    }
    public List<Url> getAll(){
        return this.repository.findAll();}
    public List<Url> getAllById(int id){
        return this.repository.findAllByUserId(id);

    }
    public void increaseVisitedCount(Url url){
        url.setVisitedCount(url.getVisitedCount()+1);
        this.repository.save(url);
    }
    public  int getId(){
        List<Url> all = this.repository.findAll();
        System.out.println(all.toString());
        return all.size()==0 ? 0 : all.stream().max(Comparator.comparingInt(Url::getId)).get().getId();

    }
      public Optional<Url> findByShortUrl(String shortUrl){
        return this.repository.findAllByShortUrl(shortUrl);
      }
}
