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
import java.util.Optional;

@Log4j2
@Service
public class PasswordUrlService {
    PasswordUrlRepository repository;
    UserRepository userRepository;
    Utilities utilities;
    UserService userService;
    MailService mailService;
    public PasswordUrlService(PasswordUrlRepository repository, UserRepository userRepository, UserService userService, MailService mailService) {
        this.repository = repository;
        this.userRepository = userRepository;
        utilities = new Utilities();
        this.userService = userService;
        this.mailService = mailService;
    }


    public boolean postForgotPassword(String email, Model model) {
        Optional<Person> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            String urlfooter = utilities.getForgotPasswordUrl();
            while (repository.countAllByPasswordUrl(urlfooter) > 0) {
                urlfooter = utilities.getForgotPasswordUrl();
            }
            String resetUrl = "/resetpassword/".concat(urlfooter);
            mailService.sendEmail(email, resetUrl);
            model.addAttribute("email", email);
            repository.save(new ForgotPasswordUrl(resetUrl, Short.valueOf("0"), byEmail.get()));
            return true;
        }
        return false;
    }

    public boolean resetUserPassword(ForgotPasswordRequest form, HttpServletRequest request) {
        String url = request.getServletPath();
        Optional<ForgotPasswordUrl> forgotPasswordUrl = repository.findByPasswordUrl(url);
        if (forgotPasswordUrl.isPresent()) {
            Person person = forgotPasswordUrl.get().getUser();
            if (form.getPassword().equals(form.getPasswordAgain())) {
                userService.resetPassword(person, form.getPassword(), url, forgotPasswordUrl.get());
                return true;
            }
        }
        return false;
    }

    public Optional<ForgotPasswordUrl> findByUrl(String url){
      return   repository.findByPasswordUrl(url);
    }
    public boolean urlIsUsed(String url){
        if(repository.findByPasswordUrl(url).isPresent()) {
            log.info(repository.findByPasswordUrl(url).toString());
            return repository.findByPasswordUrl(url).get().getUsed() == 1;
        }
        return false;
    }
    public  void  disableResetPasswordUrl(String url){
        Optional<ForgotPasswordUrl> byUrl = findByUrl(url);
        byUrl.get().setUsed( Short.valueOf("1"));
        repository.save(byUrl.get());
    }
    public String redirectResetPasswordUrl(String url){
        if(urlIsUsed(url)) return "forgot-password";
        disableResetPasswordUrl(url);
        return "reset-password";
    }
}
