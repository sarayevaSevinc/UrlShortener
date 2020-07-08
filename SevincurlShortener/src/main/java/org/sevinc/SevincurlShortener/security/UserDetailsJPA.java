package org.sevinc.SevincurlShortener.security;

import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailsJPA {

    public static PersonDetails mapper(Person person) {

        return new PersonDetails(
                person.getId(),
                person.getFullName(),
                person.getEmail(),
                person.getPassword()
        );
    }
}
