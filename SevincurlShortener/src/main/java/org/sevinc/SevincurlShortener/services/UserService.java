package org.sevinc.SevincurlShortener.services;

import org.sevinc.SevincurlShortener.Entity.Login;
import org.sevinc.SevincurlShortener.Entity.Person;
import org.sevinc.SevincurlShortener.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
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
