package petpalooza.RestControllers.userController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
import petpalooza.interfaces.IRole;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("adminRole")
@AllArgsConstructor
public class RoleController {
    IRole iRole;

    @GetMapping("list")
    public List<Role> listOfRoles(){
       return iRole.findRoles();
    }


}
