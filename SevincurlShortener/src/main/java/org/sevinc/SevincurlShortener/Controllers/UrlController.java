package org.sevinc.SevincurlShortener.Controllers;


import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.Entity.Person;
import org.sevinc.SevincurlShortener.Entity.Url;
import org.sevinc.SevincurlShortener.services.UrlService;
import org.sevinc.SevincurlShortener.Entity.Utilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Controller
@RequestMapping("/")
public class UrlController {
  UrlService service;
  Utilities utilities;

    public UrlController(UrlService service) {
        this.service = service;
        utilities = new Utilities();
    }

    @GetMapping("/mainpage")
    public String getMainPage(Model model,  HttpSession session){
        Person user= (Person) session.getAttribute("user");
        model.addAttribute("links", service.getAllById(user.getId()));
      return "main-page";
  }

  @PostMapping("/mainpage")
    public RedirectView postMainPage(@RequestParam String longUrl, HttpSession session){
        log.info(longUrl);
      Person user = (Person )session.getAttribute("user");

           Url url1 = new Url(longUrl, utilities.getShortUrl(service.getId()), utilities.getDate(), user);
         service.save(url1);
      return new RedirectView("/mainpage");
  }
  @GetMapping("/login")
    public String handleWrongUrl(Model model){
        return "login";
  }
}
