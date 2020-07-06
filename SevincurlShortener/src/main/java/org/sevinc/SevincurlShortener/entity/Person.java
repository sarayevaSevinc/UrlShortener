package org.sevinc.SevincurlShortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
//@SequenceGenerator(name="PersonSequence", sequenceName="PersonSequence")
public class Person {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY ,generator="PersonSequence"
    )
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
