package org.sevinc.SevincurlShortener.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Url {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UrlSequence")
    private int id;

    @Column(name = "longUrl", length = 4096)
    private String longUrl;

    @Column(name = "createdTime")
    private String createdTime;

    @Column(name = "shortUrl", unique = true)
    private String shortUrl;


    @Column(name = "visitedCount")
    private long visitedCount;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Person user;

    @Column(name = "expiresAt")
    private String expiresAt;

    @Column(name = "enabled")
    private Short enabled;


    public Url(String longUrl, String shortUrl, String createdTime, Person p, String date, Short enabled) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.createdTime = createdTime;
        this.visitedCount = 0;
        this.user = p;
        this.expiresAt = date;
        this.enabled = enabled;
    }
}