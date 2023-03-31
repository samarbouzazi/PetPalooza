package petpalooza.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import petpalooza.Entities.User;
import petpalooza.Services.IUser;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUser iUser;

    @GetMapping("affusr")
    public List<User> retrieveallUsers(){
        return iUser.retrieveallUsers();
    }
}
