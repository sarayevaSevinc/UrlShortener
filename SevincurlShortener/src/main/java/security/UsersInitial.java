package security;


import org.sevinc.SevincurlShortener.entity.Person;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

//@EnableAutoConfiguration
//@ComponentScan
@Configuration
public class UsersInitial {

  private final UserRepository userRepository;
  private final PasswordEncoder enc;

  public UsersInitial(UserRepository userRepository, PasswordEncoder enc) {
    this.userRepository = userRepository;
    this.enc = enc;
  }

  public void populate() {
    userRepository.saveAll(Arrays.asList(
       new Person("jim",  enc.encode("123"), "USER"),
       new Person("john", enc.encode("234"), "ADMIN"),
       new Person("jack", enc.encode("345"), "ADMIN", "USER"),
       new Person("joe",  enc.encode("456"))
    ));
  }
}
