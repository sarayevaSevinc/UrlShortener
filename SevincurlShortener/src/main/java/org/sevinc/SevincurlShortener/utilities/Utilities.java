package org.sevinc.SevincurlShortener.utilities;

import org.sevinc.SevincurlShortener.entity.db.Person;
import org.sevinc.SevincurlShortener.entity.PersonDetails;
import org.sevinc.SevincurlShortener.entity.RegistrationRequest;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utilities {

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();
        return formatter.format(dateTime);
    }
    public String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss");
        LocalDateTime time = LocalDateTime.now();
        return formatter.format(time);
    }
    public  String getShortUrl(){
        return UUID.randomUUID().toString().substring(0,7);
    }
    public  String getForgotPasswordUrl(){
        return UUID.randomUUID().toString().substring(0,7);
    }

    public boolean isPasswordSecure(String password){
        if(password.length()<6) return false;
        boolean isLower = false;
        boolean isUpper = false;
        boolean isDigit = false;

        for (Character c:password.toCharArray()) {
            if(Character.isDigit(c) )  isDigit = true;
            if(Character.isUpperCase(c)) isUpper = true;
            if(Character.isLowerCase(c)) isLower = true;
            if(isUpper && isLower && isDigit) return true;

        }
        return false;
    }
    public boolean isRegisterOk(RegistrationRequest form){
            return form.getPassword().equals(form.getPasswordAgain()) &&
                    isPasswordSecure(form.getPassword());
    }

    public Person  mapperPersonDetailsToUser(PersonDetails personDetails){
        return new Person(personDetails.getId(), personDetails.getFullName(), personDetails.getEmail(), personDetails.getPassword());
    }

    public  boolean isValid(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
