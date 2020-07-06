package security;

import org.sevinc.SevincurlShortener.entity.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class UserDetailsJPA {

    public static UserDetails mapper(Person person) {
        return User
                .withUsername(person.getEmail())
                .password(person.getPassword())
                .roles(person.getRoles())
                .build();
    }
}
