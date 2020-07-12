package org.sevinc.SevincurlShortener.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "passwordUrl")
    private String passwordUrl;

     @Column(name = "used")
     private Short used;

     @ManyToOne
     @JoinColumn(name = "userid")
      private Person user;

    public ForgotPasswordUrl(String passwordUrl) {
        this.passwordUrl = passwordUrl;
    }
    public ForgotPasswordUrl(String passwordUrl, Short used, Person person) {
        this.passwordUrl = passwordUrl;
        this.used = used;
        this.user = person;
    }
}
