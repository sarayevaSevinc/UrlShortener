package org.sevinc.SevincurlShortener.controllers;
import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.entity.Url;
import org.sevinc.SevincurlShortener.entity.UrlHistory;
import org.sevinc.SevincurlShortener.utilities.Utilities;
import org.sevinc.SevincurlShortener.services.UrlHistoryService;
import org.sevinc.SevincurlShortener.services.UrlService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

//1
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
    public RedirectView redirectUrl(@PathVariable String value, Model model, HttpServletRequest request){
       Optional<Url> url = urlService.searchUrl(value);
       if(url.isPresent()){
           urlService.increaseVisitedCount(url.get());
           urlHistoryService.add(new UrlHistory(utilities.getDate(), utilities.getTime(),request.getRemoteAddr(), url.get(),
                   url.get().getUser()));
           return new RedirectView(url.get().getLongUrl());
       }
       else{
              model.addAttribute("ex", request.getRequestURL());
           return new RedirectView("/login");
       }
    }
}
