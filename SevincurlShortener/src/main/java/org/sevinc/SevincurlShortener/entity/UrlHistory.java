package org.sevinc.SevincurlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@SequenceGenerator(name="UrlHistorySequence", sequenceName="UrlHistorySequence")
public class UrlHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UrlHistorySequence")
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private String date;

    @Column(name = "ip")
    private String ip;

    @ManyToOne
    @JoinColumn (name = "urlid")
    private Url url;

    public UrlHistory(String date, String ip) {
        this.date  = date;
        this.ip = ip;
    }
    public UrlHistory(String date, String ip, Url url) {
        this.date  = date;
        this.ip = ip;
        this.url = url;
    }
}
