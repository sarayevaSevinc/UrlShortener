package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.ForgotPasswordRequest;
import org.sevinc.SevincurlShortener.services.PasswordUrlService;
import org.sevinc.SevincurlShortener.services.UserService;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
public class ForgotPasswordController {
    UserService userService;
    Utilities utilities;
    PasswordUrlService passwordUrlService;

    public ForgotPasswordController(UserService service, PasswordUrlService passwordUrlService) {
        this.userService = service;
        utilities = new Utilities();
        this.passwordUrlService = passwordUrlService;
    }

    @GetMapping("/forgotPassword")
    public String getForgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgotPassword")
    public String postForgotPassword(@RequestParam String email, Model model) {
        return passwordUrlService.postForgotPassword(email,  model) ? "successForgotPasswordPage"
                : "forgot-Password";
    }

    @GetMapping("/resetpassword/*")
    public String getResetPassword(HttpServletRequest request) {
        return passwordUrlService.redirectResetPasswordUrl(request.getServletPath());
    }

    @PostMapping("/resetpassword/*")
    public String postResetPassword(ForgotPasswordRequest form, Model model, HttpServletRequest request) {
        if (passwordUrlService.resetUserPassword(form,  request)) return "index";
        model.addAttribute("ex", "Informations are not true. Please, try again");
        return "reset-password";
    }
}
