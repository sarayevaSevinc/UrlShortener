package org.sevinc.SevincurlShortener.Services;

import org.hibernate.criterion.Example;
import org.sevinc.SevincurlShortener.Entity.Url;
import org.sevinc.SevincurlShortener.Repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

     private UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }
    public void save(Url  url){
        this.repository.save(url);
    }
    public Optional <Url> searchUrl(String longUrl){
       return this.repository.findAll().stream().filter(url-> url.getLongUrl().equals(longUrl)).findAny();

    }
    public List<Url> getAll(){
        return this.repository.findAll();}

    public void increaseVisitedCount(Url url){
        this.repository.delete(url);
        url.setVisitedCount(url.getVisitedCount()+1);
        this.repository.save(url);
    }
    public  int getId(){
        List<Url> all = this.repository.findAll();
        return all.size()==0 ? 0 : all.stream().max((o1, o2) -> o2.getId()-o1.getId()).get().getId();

    }
}
