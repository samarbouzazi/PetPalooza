package petpalooza.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

import java.util.List;

@Service
@Data
public class UserService implements IUser{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> retrieveallUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User addeUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deletUser(Long id) {
        userRepository.deleteById(id);
    }
}
