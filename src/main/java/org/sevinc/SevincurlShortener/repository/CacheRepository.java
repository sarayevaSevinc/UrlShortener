package org.sevinc.SevincurlShortener.repository;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.db.Url;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

@Log4j2
@Repository
public class CacheRepository {
    private static final HashMap<Integer, Url> cache = new HashMap<>();

    public void  add (Url url){
        log.info("adding new Url into the cache... ");
        cache.put(url.getId(), url);
    }
    public Optional<Url> searchWithShortUrl(String shortUrl){
        log.info("searching  Url into the cache... " + shortUrl);
        return cache.values().stream().filter(x-> x.getShortUrl().equals(shortUrl)).findAny();
    }
    public  void update (Url url){
        if(cache.keySet().stream().filter(x->x==url.getId()).findAny().isPresent())
        cache.replace(url.getId(), url);
    }
    public void remove (int id){
        cache.remove(Integer.valueOf(id));
  }
    public HashMap<Integer, Url> getAll(){
        return cache;
  }
    public  int getSizeOfCache(){
          return cache.size();
  }
  public int getFirstInUrlId(){
      return   cache.keySet().stream().min(Comparator.comparingInt(o -> o)).get();
  }

}
