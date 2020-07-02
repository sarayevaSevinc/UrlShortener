package org.sevinc.SevincurlShortener.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class Person {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

//    @OneToMany
//    @JoinColumn(name = "userid", referencedColumnName = "id")
//    private List<Url> urls;

    public Person( String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
