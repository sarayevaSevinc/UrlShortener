package org.sevinc.SevincurlShortener.controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.sevinc.SevincurlShortener.services.UrlHistoryService;
import org.sevinc.SevincurlShortener.services.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/")
public class RedirectController {
    UrlService urlService;
    UrlHistoryService urlHistoryService;
    Utilities utilities;

    public RedirectController(UrlService urlService, UrlHistoryService urlHistoryService) {
        this.urlService = urlService;
        this.urlHistoryService = urlHistoryService;
        this.utilities = new Utilities();
    }

    @GetMapping("/{value}")
    public RedirectView redirectUrl(@PathVariable String value, HttpServletRequest request) {
        return  new RedirectView(urlService.redirectUrl(value, request.getRemoteAddr()));
    }
}
