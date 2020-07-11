package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.entity.db.Url;
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
        model.addAttribute("links", service.getAllById(principal.getId()));
        model.addAttribute("hidden", "hidden");
        return "main-page";
    }

    @PostMapping("/mainpage")
    public RedirectView postMainPage(@RequestParam String longUrl, Authentication auth) {
        if (utilities.isValid(longUrl)) {
            PersonDetails principal = (PersonDetails) auth.getPrincipal();
            service.save(new Url(longUrl, utilities.getShortUrl(),
                    utilities.getDate(),
                    utilities.mapperPersonDetailsToUser(principal)));
        }
        return new RedirectView("/mainpage");
    }

    @GetMapping("/login")
    public String handleWrongUrl() {
        return "login";
    }


    @GetMapping("/mainpage2")
    public String postUrlHistory(@RequestParam int id, Model model, Authentication authentication) {
        PersonDetails person = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("links", service.getAllById(person.getId()));
        model.addAttribute("histories", urlHistoryService.getAllByUrlIdAndUserId(id, person.getId()));
        log.info(id);
        return "main-page2";
    }
}
