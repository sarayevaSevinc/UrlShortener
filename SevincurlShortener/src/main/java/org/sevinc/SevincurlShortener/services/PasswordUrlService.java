package org.sevinc.SevincurlShortener.services;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.ForgotPasswordRequest;
import org.sevinc.SevincurlShortener.entity.db.ForgotPasswordUrl;
import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.repository.PasswordUrlRepository;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Log4j2
@Service
public class PasswordUrlService {
    PasswordUrlRepository repository;
    UserRepository userRepository;
    Utilities utilities;
    UserService userService;

    public PasswordUrlService(PasswordUrlRepository repository, UserRepository userRepository, UserService userService) {
        this.repository = repository;
        this.userRepository = userRepository;
        utilities = new Utilities();
        this.userService = userService;
    }


    public boolean postForgotPassword(String email, HttpSession session, Model model) {
        Optional<Person> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            String resetUrl = "/resetpassword/" + utilities.getForgotPasswordUrl();
            log.info(resetUrl);
            model.addAttribute("email", email);
            repository.save(new ForgotPasswordUrl(resetUrl, Short.valueOf("0"), byEmail.get()));
            return true;
        }
        return false;
    }

    public boolean resetUserPassword(ForgotPasswordRequest form, HttpServletRequest request) {
        String url = request.getRequestURL().toString().substring(21);
        Optional<ForgotPasswordUrl> forgotPasswordUrl = repository.findByPasswordUrl(url);
        log.info(forgotPasswordUrl.toString());
        if (forgotPasswordUrl.isPresent()) {
            log.info("I am here ");
            Person person = forgotPasswordUrl.get().getUser();
            log.info(forgotPasswordUrl.get().toString());
            log.info(person.toString());
            log.info(form.toString());
            if (form.getFullName().equals(person.getFullName()) && form.getPassword().equals(form.getPasswordAgain())) {
                userService.resetPassword(person, form.getPassword(), url, forgotPasswordUrl.get());
                log.info("i am inside of if");
                return true;
            }
        }
        return false;
    }

    public Optional<ForgotPasswordUrl> findByUrl(String url){
      return   repository.findByPasswordUrl(url);
    }
    public boolean urlIsUsed(String url){
        if(repository.findByPasswordUrl(url).isPresent())
        return  repository.findByPasswordUrl(url).get().getUsed()==1;
        return false;
    }
    public  void  disableResetPasswordUrl(String url){
        Optional<ForgotPasswordUrl> byUrl = findByUrl(url);
        byUrl.get().setUsed( Short.valueOf("1"));
        repository.save(byUrl.get());
    }
    public String redirectResetPasswordUrl(String url){
        log.info(url);
        if(urlIsUsed(url)) return "forgot-password";
        disableResetPasswordUrl(url);
        return "reset-password";
    }
}
