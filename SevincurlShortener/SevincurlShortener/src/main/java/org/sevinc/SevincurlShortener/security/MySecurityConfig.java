package org.sevinc.SevincurlShortener.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin()
                .loginPage("/index")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/landing").and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/css/**", "/js/**", "/img/**")
                .permitAll()
                .antMatchers("/", "/register", "/index", "forgotPassword")
                .not().authenticated()
                .antMatchers("/mainpage", "/mainpage2", "/landing")
                .authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/index");
    }
}
