package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.services.UrlHistoryService;
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
    UrlHistoryService urlHistoryService;

    public UrlController(UrlService service, UrlHistoryService urlHistoryService) {
        this.service = service;
        utilities = new Utilities();
        this.urlHistoryService = urlHistoryService;
    }

    @GetMapping("/mainpage")
    public String getMainPage(Model model, Authentication auth) {
        PersonDetails principal = (PersonDetails) auth.getPrincipal();
        model.addAttribute("links", service.getAllByUserId(principal.getId()));
        model.addAttribute("name", principal.getFullName());
        return "main-page";
    }

    @PostMapping("/mainpage")
    public RedirectView postMainPage(@RequestParam String longUrl, @RequestParam(defaultValue = "60") String exDate, Authentication auth) {
        service.createAndSaveNewUrl(longUrl, exDate, (PersonDetails) auth.getPrincipal());
        return new RedirectView("/mainpage");
    }

    @GetMapping("/exception")
    public String handleWrongUrl() {
        return "exceptionPage";
    }

    @PostMapping("/updateEnabled")
    public RedirectView updateEnabled(@RequestParam int id, @RequestParam(defaultValue = "false") boolean enabled, Authentication auth) {
        service.updateEnabled(id, enabled, (PersonDetails) auth.getPrincipal());
        return new RedirectView("/mainpage");
    }

    @GetMapping("/urlHistoryModalWindow*")
    public String postUrlHistory(@RequestParam int id, Model model, Authentication authentication) {
        PersonDetails person = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("links", service.getAllByUserId(person.getId()));
        model.addAttribute("name", person.getFullName());
        model.addAttribute("histories", urlHistoryService.getAllByUrlIdAndUserId(id, person.getId()));
        model.addAttribute("shortlink", "https://short-urlapp.herokuapp.com/".concat(service.getUrlById(id).getShortUrl()));
        log.info(id);
        return "urlHistoryModalWindow";
    }
}
