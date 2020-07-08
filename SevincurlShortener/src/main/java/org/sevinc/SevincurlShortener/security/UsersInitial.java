package org.sevinc.SevincurlShortener.security;


import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UsersInitial {

  private final UserRepository userRepository;
  private final PasswordEncoder enc;

  public UsersInitial(UserRepository userRepository, PasswordEncoder enc) {
    this.userRepository = userRepository;
    this.enc = enc;
  }


}
