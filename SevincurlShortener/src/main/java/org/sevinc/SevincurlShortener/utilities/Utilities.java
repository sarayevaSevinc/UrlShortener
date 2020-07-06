package org.sevinc.SevincurlShortener.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utilities {
   List<Character> alphabet = Arrays.asList('#', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
           'j', 'k', 'l', 'm', 'n', 'o', 'p','q','r', 's','t', 'u', 'v', 'w','x','y','z',
           'A','B','C', 'D','E', 'F','G','H','I','J','K','L','M','N', 'O','P', 'Q', 'R','S','T','U','V','W',
           'X','Y','Z','0','1','2','3','4','5','6','7','8','9');


    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();
        return formatter.format(dateTime);
    }
    public  String getShortUrl(){
//        List<Integer> list = new ArrayList<>();
//        int temp = (int)id+1;
//        System.out.println(temp);
//        System.out.println(id+1);
//        while (temp>0){
//            list.add(temp%62);
//            temp = temp/62;
//        }
//        System.out.println(alphabet.toString());
//        System.out.println(list.toString());
//        System.out.println(alphabet.get(list.get(0)));
//        String url = "http://localhost:8080/";
//        return  url.concat(IntStream.range(0, list.size()).map(x-> list.size()-1-x)
//                .map(x-> list.get(x)).mapToObj(x-> String.valueOf(alphabet.get(x)))
//                .collect(Collectors.joining()));
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
    public  boolean isEmailTrue(String email){
        if(!email.contains("@")) return false;
        if(!email.split("@")[1].contains(".")) return false;
        return true;
    }
//    public String getDate(){
//        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//    }
}