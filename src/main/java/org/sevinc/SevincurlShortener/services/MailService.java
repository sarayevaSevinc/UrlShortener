package org.sevinc.SevincurlShortener.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("UrlShortenerAppBySevinc");
        message.setTo(email);
        message.setSubject("Password Reset Link");
        message.setText("Hi!  Welcome back to UrlShortenerAppBySevinc! " +
                "You can reset your password with this link! " + "urlshortenerappbysevinc.herokuapp.com".concat(link)
                .concat("\n Sincerely, UrlShortenerApp!"));

        mailSender.send(message);
    }
}
