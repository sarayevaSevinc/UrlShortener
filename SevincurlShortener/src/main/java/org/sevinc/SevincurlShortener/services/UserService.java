package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.db.ForgotPasswordUrl;
import org.sevinc.SevincurlShortener.entity.LoginRequest;
import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.repository.PasswordUrlRepository;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Log4j2
@Service
public class UserService  {
UserRepository userRepository;
PasswordUrlRepository urlRepository;
    @Autowired
    PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordUrlRepository urlRepository ) {
        this.userRepository = userRepository;
        this.urlRepository = urlRepository;
    }

    public void add(Person user){
        userRepository.save(user);
    }
    public Optional <Person> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<Person> getPerson(LoginRequest login){
     return   userRepository.findAllByEmailAndPassword(login.getEmail(), login.getPassword());
    }
   public  void resetPassword(Person person, String password, String url){
        person.setPassword(encoder.encode(password));
        userRepository.save(person);
        urlRepository.save(new ForgotPasswordUrl(url));
   }

}

