package org.sevinc.SevincurlShortener.Entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "longUrl")
    private String longUrl;

      @Column(name = "visitedTime")
     private String visitedTime;

    @Column(name = "shortUrl")
    private String shortUrl;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "visitedCount")
    private long visitedCount;

    public Url( String longUrl, String shortUrl, String visitedTime) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.visitedTime = visitedTime;

    }

}
