# UrlShortener
Url Shortener App created by Sevinc Sarayeva for the final project of Iba Tech Academy.
Url Shortener is a link management tool that allows you to create new short links from long URLs and to take control of your links like sharing, analyzing of your short URLs.
Written in Java Programming language.
    Used tools:
    Spring Framework
    Spring Security
    Spring MVC
    For Database:
       PostgreSQL
       Hibernate
    For Front-End
      HTML
      CSS
      Thymeleaf.
  
 
User can Register or if he/she has an account already, he/she can log in. Successful Login will redirect the user to the Landing Page.
By Clicking the "Get Started" button user can go to MainPage. In the main page, there are 2 input box, first one for long URL which has to be shortened, 
and it's expired time ( this is optional, if the user doesn't select it, the default time is 5 years).
When the user clicks the "SHORTEN" button, a unique short URL is created and is shown on the MainPage. From that time, the user can copy and use it anywhere he/she can.
And from that time that short URL is being available for everyone.
When this short URL has requested, the server will redirect it automatically to it's original URL.
Users can see his/her own short link history, IP address, and date time in which URL has clicked.

Deployed to the heroku.com.
Link : https://short-urlapp.herokuapp.com/
