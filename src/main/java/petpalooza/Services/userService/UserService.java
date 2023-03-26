package petpalooza.Services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> listOfUsers(){
        return userRepository.findAll();}

//    public User addNewUser(User user){
//    return userRepository.save(user);
//    }

    public User save(User user) {

        /////Decrypt password in the registration process with BCrypt
     PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
       user.setPassword(hashedPassword);
        return userRepository.save(user);
    }




    public User findUserByID(Long idUser){
        return userRepository.findById(idUser).orElse(null);
    }
    public void deleteUser(Long idUser){
        userRepository.deleteById(idUser);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }


}
