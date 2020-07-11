package org.sevinc.SevincurlShortener.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sevinc.SevincurlShortener.entity.db.Person;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Url {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="UrlSequence")
    private int id;

    @Column (name = "longUrl", unique = true, length = 4096)
    private String longUrl;

      @Column(name = "createdTime")
     private String createdTime;

    @Column(name = "shortUrl" , unique = true)
    private String shortUrl;


    @Column(name = "visitedCount")
    private long visitedCount;

     @ManyToOne
     @JoinColumn(name = "userid")
     private Person user;


//    public Url( String longUrl, String shortUrl, String createdTime) {
//        this.longUrl = longUrl;
//        this.shortUrl = shortUrl;
//        this.createdTime = createdTime;
//       this.visitedCount = 0;
//    }

    public Url( String longUrl, String shortUrl, String createdTime, Person p) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdTime = createdTime;
        this.visitedCount = 0;
        this.user = p;
    }
//    public Url(int id, String longUrl, String shortUrl, String createdTime, Person p) {
//        this.id = id;
//        this.longUrl = longUrl;
//        this.shortUrl = shortUrl;
//        this.createdTime = createdTime;
//        this.visitedCount = 0;
//        this.user = p;
//    }
}
