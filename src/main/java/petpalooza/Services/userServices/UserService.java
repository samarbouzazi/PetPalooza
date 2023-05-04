package petpalooza.Services.userServices;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;
import petpalooza.security.payload.response.MessageResponse;

import java.util.List;
import java.util.Optional;

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
    public void blockUser(Long idUser) {
       Block(idUser);


    }



    public void Block(Long idUser){
        User user=new User();
        user=userRepository.findById(idUser).orElse(null);

        user.setActive(0);

        userRepository.save(user);
        System.out.println("\n user blocked succesfully!!!");


    }


    @Override
    public ResponseEntity<?> SignalerUser(Long idUser) {

        User user=new User();
        user= findUserByID(idUser);
        int i= user.getNumberOfSignal();
        System.out.println("the number of Signal " + i);
        if(i ==5){
            Block(idUser);
            System.out.println("User blocked succusfuly\n -----");
            return ResponseEntity.ok(new MessageResponse("user blocked succusfuly after 5 signal"));
        }else {
            int newValue=i+1;
            user.setNumberOfSignal(newValue);
            System.out.println("\n  and now is " + i);
            userRepository.save(user);

            System.out.println(" and know the number of Signal  after saving  " + i);
            return ResponseEntity.ok(new MessageResponse("and now the number of Signal  after saving is  " +i));


        }
    }

    @Override
    public List<User> getRole(Long idUser) {
        return  null;
    }


}