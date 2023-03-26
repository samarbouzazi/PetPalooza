package petpalooza.Services.userService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Role;
import petpalooza.Repositories.RoleRepository;
import petpalooza.interfaces.IRole;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService  implements IRole {
    @Autowired
    RoleRepository roleRepository;


    @Override
    public void affectRoleToUser() {

    }

    @Override
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }


}
