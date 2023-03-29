package petpalooza.RestControllers.userController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.ERole;
import petpalooza.Entities.Role;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.userService.CustomException;
import petpalooza.Services.userService.UserService;
import petpalooza.Entities.User;
import petpalooza.interfaces.IUser;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("admin/user")
public class UserController {
@Autowired
    IUser iUser;

@Autowired
    UserRepository userRepository;



    @PostMapping("/register")
    public User register(@RequestBody User user){
        return iUser.addNewUser(user );
    }

    @PutMapping("/updateUser")
    public void updateUser( @RequestBody User user, HttpServletResponse response) throws IOException {
        iUser.updateUser(user);
        response.sendRedirect("http://localhost:8888/admin/list");
    }
    @GetMapping("/find/{idUser}")
    public User retriveUserByID(@PathVariable("idUser")  Long idUser){
        return  iUser.findUserByID(idUser);

    }
    @GetMapping("/list")
    public List<User> retrieveAllUser(){
        return iUser.getAllUser();
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable("id")  Long id){
          return ResponseEntity.ok(new CustomException("User "+
                  iUser.findUserByID(id).getUsername()
                  +"delete it  successfully!!!"));
    }




//    @PostMapping("/signIn")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {
//        if (userRepository.existsByUsername(user.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new CustomException("Error: Username"+ user.getUsername() + "  is already taken!, please try another one "));
//        }
//
//        if (iUser.emailExist(user.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new CustomException("Error: Email address "+ user.getUsername() + "  is already used !, please check your input "));
//        }
//
//        iUser.addNewUser(user);
//        return ResponseEntity.ok(new CustomException("User registered successfully!!!"));
//
//    }
//



    //    @GetMapping("pist/{id}")
//    @JsonIgnore
//    public User findByPist(@PathVariable("id") Long id){
//        return (Skieur) iSkieur.findbyPist(id);
//
//    }


//    @GetMapping("/UserList/{roleName}")
//    public List<User> retriveUsersByID(@PathVariable("roleName") ERole roleName){
//        return  userService.findByRole(roleName);
//
//    }
}
