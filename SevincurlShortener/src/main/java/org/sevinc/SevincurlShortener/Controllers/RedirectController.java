package org.sevinc.SevincurlShortener.Controllers;

import lombok.extern.log4j.Log4j2;
import org.sevinc.SevincurlShortener.Entity.Url;
import org.sevinc.SevincurlShortener.services.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

//1
@Log4j2
@Controller
@RequestMapping("/")
public class RedirectController {
    UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{value}")
    public RedirectView redirectUrl(@PathVariable String value, Model model, HttpServletRequest request, HttpSession session){
        log.info(request.getRequestURL()+ "   this is short urlin long url");
       Optional<Url> url = urlService.searchUrl(request.getRequestURL().toString());
       if(url.isPresent()){
           urlService.increaseVisitedCount(url.get());
           return new RedirectView(url.get().getLongUrl());
       }
       else{
           log.info(request.getRequestURL());
              model.addAttribute("ex", request.getRequestURL());
           return new RedirectView("/login");
       }
    }
}
