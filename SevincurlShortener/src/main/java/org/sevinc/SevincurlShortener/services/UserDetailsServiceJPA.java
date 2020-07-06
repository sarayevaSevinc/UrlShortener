package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
@Configuration
public class UserDetailsServiceJPA implements UserDetailsService {
    UserRepository userRepository;

    public UserDetailsServiceJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserDetails mapper_to_UserDetails(Person user) {
        return new PersonDetails(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword()
        );
    };
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(String.format(">>>> loading user details for user: %s", email));
        return userRepository.findByEmail(email)
                .map(UserDetailsServiceJPA::mapper_to_UserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB", email)
                ));
    }
}
