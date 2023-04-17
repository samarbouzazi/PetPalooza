package petpalooza.security.predifinedClasses;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;


@Service
    public class UserPrincipalDetailsService implements UserDetailsService {
        private UserRepository userRepository;

        public UserPrincipalDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = this.userRepository.findByUsername(s);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            return userPrincipal;
        }
    }