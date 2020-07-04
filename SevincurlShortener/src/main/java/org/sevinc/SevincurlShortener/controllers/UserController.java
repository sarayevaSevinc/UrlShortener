package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.SignUp;
import org.sevinc.SevincurlShortener.entity.Login;
import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.entity.Utilities;
import org.sevinc.SevincurlShortener.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/")
public class UserController {
    UserService service;
    Utilities utilities;

    public UserController(UserService service) {
        this.service = service;
        utilities = new Utilities();
    }

    @GetMapping("/index")
    public String getLogin() {
        return "index";
    }

    @PostMapping("/index")
    public String postLogin(Login login, HttpSession session, Model model) {
        Optional<Person> person = service.getPerson(login);
        log.info("you are in postLogin");
        if (person.isPresent()) {
            session.setAttribute("user", person.get());
            return "landing";
        }
        model.addAttribute("ex", "Username or password is incorrect. Please, try again");
        return "index";

    }


    @GetMapping("/register")
    public String getRegistration(Model model) {
        return "registration";
    }

    @GetMapping("/landing")
    public String getLanding() {
        return "landing";
    }

    @PostMapping("/landing")
    public RedirectView postLanding() {
        return new RedirectView("/mainpage");
    }

    @PostMapping("/register")
    public String postRegistration(SignUp form, Model model) {
        String ex = new String("");
        if (!form.getPassword().equals(form.getPasswordAgain())) {
            log.info("i am here");
            ex = ex.concat("Passwords are not the same");

        } else {
            if (!utilities.isPasswordSecure(form.getPassword())) ex = ex.concat("Password is not secure, please, re enter " +
                    "the informations");
            else {
                if (!utilities.isEmailTrue(form.getEmail())) ex = ex.concat("Email is not valid. Please, enter valid email");
                else {
                    service.add(new Person(form.getFullName(), form.getEmail(), form.getPassword()));
                    return "index";
                }
            }
        }
        log.info(ex);
        model.addAttribute("ex", ex);
        return "registration";

    }
}
