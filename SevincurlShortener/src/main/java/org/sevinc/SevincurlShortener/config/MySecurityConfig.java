package org.sevinc.SevincurlShortener.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
@Log4j2
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/", "/register", "/index", "forgotPassword").permitAll()
                .antMatchers("/mainpage", "/landing").authenticated()
                .anyRequest().authenticated();

        http
                .rememberMe();

        http
                .formLogin() .loginPage("/index")
        // .usernameParameter("email")
         // .passwordParameter("password")
         .successForwardUrl("/landing")
        .failureForwardUrl("/index")
//        .failureHandler() Exceptions handling
                .permitAll();

    }
}
