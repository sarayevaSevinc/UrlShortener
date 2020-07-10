package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class ForgotPasswordController {

    @GetMapping("/forgot")
    public String getForgotPassword(){
        return "forgot-password";
    }
    @PostMapping("/forgot")
    public String postForgotPassword(@RequestParam String email){
        log.info(email);
        return "forgot-password";
    }
    
}
