package petpalooza.RestControllers.userController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Services.userService.UserService;
import petpalooza.Entities.User;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
@Autowired
UserService userService;


    // interface !! ::: visiblity
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/updateUser")
    public User updateUser( @RequestBody User user){
        return userService.updateUser(user);
    }
    @GetMapping("/find/{id}")
    public User retriveUserByID(@PathVariable("idUser")  Long idUser){
        return  userService.findUserByID(idUser);

    }
    @GetMapping("/list")
    public List<User> retrieveAllUser(){
        return userService.listOfUsers();
    }


    @DeleteMapping("delete/{id}")
    public void deleteSkieur( @PathVariable("id")  Long id){
        userService.deleteUser(id);
    }


//    @GetMapping("pist/{id}")
//    @JsonIgnore
//    public User findByPist(@PathVariable("id") Long id){
//        return (Skieur) iSkieur.findbyPist(id);
//
//    }





}
