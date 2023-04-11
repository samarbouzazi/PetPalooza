package petpalooza.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;
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
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addNewUser(User user) {
        String name=user.getUsername();
        if((userRepository.existsByUsername(name)) ==true){
            System.out.println("user exist ");
        }

        /////Decrypt password in the registration process with BCrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
//       Set<Role> s1 =new HashSet<Role>();
//       s1.add(role);
//       user.setRoles((s1));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);

    }

    @Override
    public User findUserByID(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public Boolean usernameAlreadyExist(String username) {
        if(userRepository.existsByUsername(username)){
            System.out.println("User "+ username + " already exist"+" \n");
            return true;
        }
        return false;
    }


    @Override
    public Boolean emailExist(String email) {
        if(userRepository.existsByEmail(email)){
            System.out.println("Email "+ email + " already exist"+" \n");
            return true;
        }
        return false;
    }

    @Override
    public User getById(long id) {

        return userRepository.findById(id).get();

    }
}


