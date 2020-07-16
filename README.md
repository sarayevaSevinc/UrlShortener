# UrlShortener

Url Shortener app created by Sevinc for the final project for Iba Tech Academy.
"URL SHORTENER" is a link management tool that allows you to create new short links from long URLs and to take control of your links like sharing, 
analyzing of your short URLs.
Used Technologies: Spring Framework, Spring MVC, Spring Security, 
For Database:PostgreSQL, Hibernate, For front-end : HTML, CSS, Tyhmeleaf.

User can register or if he/she has an account can log in. Successfully login will redirect the user to the Landing Page. By clicking the "GET STARTED" button 
user will be in MainPage. There are 2 input boxes on the main page. 
The first one is to enter the long URL which will be shortened, and the other one it's expiration date(it is optional, so if the user doesn't select, 
it's default value is 5 years).
Then the user hits the "SHORTEN" button, and a unique short link has been created and has added into the new row. From then the user can share it anywhere he/she want.
That short link will be available for everyone from then.
When that short link has been requested, the server will redirect this request to the original URL. 
Every user can see his/her links visited history ( Visited Date, visited Time, IP address) and enable/disable it.

The project deployed to the Heroku.

Link: https://short-urlapp.herokuapp.com
