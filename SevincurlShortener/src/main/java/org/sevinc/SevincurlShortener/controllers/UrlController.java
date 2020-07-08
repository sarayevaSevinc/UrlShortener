package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.entity.Url;
import org.sevinc.SevincurlShortener.services.UrlService;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


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
    public String getMainPage(Model model, Authentication auth){
        PersonDetails principal = (PersonDetails) auth.getPrincipal();
        model.addAttribute("links", service.getAllById(principal.getId()));
      return "main-page";
  }

  @PostMapping("/mainpage")
    public RedirectView postMainPage(@RequestParam String longUrl, Authentication auth){
      PersonDetails principal = (PersonDetails) auth.getPrincipal();
         service.save( new Url(longUrl, utilities.getShortUrl(),
                 utilities.getDate(),
                 utilities.mapperPersonDetailsToUser(principal)));
        return new RedirectView("/mainpage");
  }
  @GetMapping("/login")
    public String handleWrongUrl(Model model){
        return "login";
  }

  @GetMapping("/history/{id}")
    public RedirectView getUrlHistory(@PathVariable  int id){
        log.info(id);
      return new RedirectView("/mainpage");
  }
}
