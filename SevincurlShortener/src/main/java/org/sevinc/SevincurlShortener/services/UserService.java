package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.Login;
import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Log4j2
@Service
public class UserService  {
UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(Person user){
        userRepository.save(user);
    }

    public Optional<Person> getPerson(Login login){
     return   userRepository.findAllByEmailAndPassword(login.getEmail(), login.getPassword());
    }


}

