package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.RegistrationRequest;
import org.sevinc.SevincurlShortener.entity.db.ForgotPasswordUrl;
import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.repository.PasswordUrlRepository;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class UserService  {
    UserRepository userRepository;
    PasswordUrlRepository urlRepository;
    Utilities utilities;
    @Autowired
    PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordUrlRepository urlRepository ) {
        this.userRepository = userRepository;
        this.urlRepository = urlRepository;
        utilities = new Utilities();
    }

    public void add(Person user){
        userRepository.save(user);
    }

    public  void resetPassword(Person person, String password, String url, ForgotPasswordUrl forgotPasswordUrl){
        person.setPassword(encoder.encode(password));
        userRepository.save(person);
        forgotPasswordUrl.setUsed(Short.valueOf("1"));
        urlRepository.save(forgotPasswordUrl);
    }

    public boolean registerUser(RegistrationRequest form){
        boolean flag = utilities.isRegisterOk(form);
        if (flag) {
            add(new Person(form.getFullName(), form.getEmail(), encoder.encode(form.getPassword())));
            return true;
        }
        return false;
    }

}

