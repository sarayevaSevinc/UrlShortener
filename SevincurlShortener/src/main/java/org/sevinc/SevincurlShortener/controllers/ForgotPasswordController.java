package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.ForgotPasswordForm;
import org.sevinc.SevincurlShortener.entity.Person;
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
import java.util.Optional;

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
    public String getForgotPassword(){
        return "forgot-password";
    }

    @PostMapping("/forgotPassword")
    public String postForgotPassword(@RequestParam String email, HttpSession session, Model model){
        Optional<Person> byEmail = userService.findByEmail(email);
        if(byEmail.isPresent()){
            session.setAttribute("email", email);
            session.setAttribute("predictUser", byEmail.get());
            String resetUrl = "/resetpassword/" + utilities.getForgotPasswordUrl();
            model.addAttribute("url", resetUrl);
            return "reset-password";
        }
        return "forgot-Password";
    }

//    @GetMapping("/resetpassword*")
//    public String getResetPassword(HttpSession session, HttpServletRequest request){
//        String url = request.getRequestURL().toString();
//        if(!passwordUrlService.getByPasswordUrl(request.getRequestURL().toString()).isPresent()){
//            Person predictUser = (Person) session.getAttribute("predictUser");
//            String email = String.valueOf(session.getAttribute("email"));
//            passwordUrlService.save(new ForgotPasswordUrl( url));
//            return "reset-password";
//        }
//        return "forgot-password";
//    }
    @PostMapping("/resetpassword/*")
    public String postResetPassword(ForgotPasswordForm form, HttpSession session, Model model, HttpServletRequest request){
        String url = request.getRequestURL().toString();
        if(!passwordUrlService.getByPasswordUrl(url).isPresent()) {
            Person person = (Person) session.getAttribute("predictUser");
            if (form.getFullName().equals(person.getFullName()) && form.getPassword().equals(form.getPasswordAgain())) {
                userService.resetPassword(person, form.getPassword(),url);
                return "index";
            }
        }
      model.addAttribute("ex", "Informations are not true. Please, try again");
      return   "reset-password";
    }
}
