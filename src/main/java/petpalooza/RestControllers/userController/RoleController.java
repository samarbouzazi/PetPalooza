package petpalooza.RestControllers.userController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petpalooza.Entities.Role;
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
