package petpalooza.Services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

@Service
@AllArgsConstructor
public class VerifyAccountService implements IVerifyAccount{
    UserRepository userRepository;
    @Override
    public void UpdateActiveStatus(String email) {
       User user= getUserByEmail(email);
       user.setActive(1);
       userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return  userRepository.findByEmail(email);

    }
}
