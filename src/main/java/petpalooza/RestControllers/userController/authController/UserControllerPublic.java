package petpalooza.RestControllers.userController.authController;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IUser;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UserControllerPublic {
    @Autowired
    IUser iUser;

    @PutMapping("/updateUser")
    public User updateEvent(@RequestBody User user){
        return iUser.updateUser(user);
    }

}
