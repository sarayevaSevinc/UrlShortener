package security;


import lombok.extern.slf4j.Slf4j;
import org.sevinc.SevincurlShortener.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceJPA implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(String.format(">>>>>>> UserDetails.loadUserByUsername:%s", email));
        return userRepository.findByEmail(email).map(UserDetailsJPA::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User `%s` not found", email)));
    }
}
