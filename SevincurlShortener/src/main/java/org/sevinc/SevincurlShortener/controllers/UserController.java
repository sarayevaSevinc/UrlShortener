package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.SignUp;
import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.sevinc.SevincurlShortener.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/")
public class UserController {
    UserService service;
    Utilities utilities;
    @Autowired
    PasswordEncoder encoder;

    public UserController(UserService service) {
        this.service = service;
        utilities = new Utilities();
    }

    @GetMapping("/index")
    public String getLogin() {
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
        boolean flag = utilities.isRegisterOk(form);
        String ex = flag ? new String(""): " Please, enter correct informations. " ;
        if(flag) {
            service.add( new Person(form.getFullName(), form.getEmail(), encoder.encode(form.getPassword())));
            return "index";
        }
        model.addAttribute("ex", ex);
        return "registration";
    }


}