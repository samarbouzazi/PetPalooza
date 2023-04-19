package petpalooza.Services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

@AllArgsConstructor
@Service
public class ForgetPwdService implements  IForgetPwd{


    UserRepository userRepository;
    @Override
    public void updatePassword(User user, String newPassword)  {

        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        String encodedPWD= passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user != null){
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }else {
            throw new UsernameNotFoundException("cloud not find any user with username   eamil   s: "+ email);
        }

    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
}
