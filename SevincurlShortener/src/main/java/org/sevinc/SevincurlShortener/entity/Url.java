package org.sevinc.SevincurlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="UrlSequence", sequenceName="UrlSequence")
public class Url {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UrlSequence")
    private int id;

    @Column (name = "longUrl")
    private String longUrl;

      @Column(name = "visitedTime")
     private String visitedTime;

    @Column(name = "shortUrl")
    private String shortUrl;


    @Column(name = "visitedCount")
    private long visitedCount;

     @ManyToOne
     @JoinColumn(name = "userid")
     private Person user;


    public Url( String longUrl, String shortUrl, String visitedTime) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.visitedTime = visitedTime;
       this.visitedCount = 0;
    }

    public Url( String longUrl, String shortUrl, String visitedTime, Person p) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.visitedTime = visitedTime;
        this.visitedCount = 0;
        this.user = p;
    }
}