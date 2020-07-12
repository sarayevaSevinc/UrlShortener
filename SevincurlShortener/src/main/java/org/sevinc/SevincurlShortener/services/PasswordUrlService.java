package org.sevinc.SevincurlShortener.services;

import org.sevinc.SevincurlShortener.entity.ForgotPasswordRequest;
import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.repository.PasswordUrlRepository;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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
            session.setAttribute("email", email);
            session.setAttribute("predictUser", byEmail.get());
            String resetUrl = "/resetpassword/" + utilities.getForgotPasswordUrl();
            model.addAttribute("url", resetUrl);
            return true;
        }
        return false;
    }

    public boolean resetUserPassword(ForgotPasswordRequest form, HttpSession session, Model model, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (!repository.findByPasswordUrl(url).isPresent()) {
            Person person = (Person) session.getAttribute("predictUser");
            if (form.getFullName().equals(person.getFullName()) && form.getPassword().equals(form.getPasswordAgain())) {
                userService.resetPassword(person, form.getPassword(), url);
                return true;
            }
        }
        return false;
    }
}
