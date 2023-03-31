package petpalooza.security.predifineInterfaces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
/*
 The UserDetailsService is a core interface in Spring Security framework, which is used to retrieve the user’s authentication and authorization information.
 loadUserByUsername() which we can implement to feed the customer information to the Spring security API.
 The DaoAuthenticationProvider(security config) will use this information to load the user information (pwd, email ..) during authentication process. Here is the definition of the interface.
    @Bean
     public DaoAuthenticationProvider authProvider() {
      ///
              authProvider.setUserDetailsService(userDetailsService);
              reutn authProvider}
 */