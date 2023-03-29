package petpalooza.Services.userService;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.ERole;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
import petpalooza.Repositories.RoleRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.RestControllers.userController.RoleController;

import java.util.*;

@Service
public class DbInit  implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
   PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//////////////////////////////////////////////////////////

        this.roleRepository.deleteAll();
        Role RoleAdmin= new Role(ERole.ROLE_ADMIN);
        Role RoleUser= new Role(ERole.ROLE_USER);
        Role RoleAssocition =new Role(ERole.ROLE_ASSOSITION);
        Role RoleManager=new Role(ERole.ROLE_MANGER);

        List<Role> roles=Arrays.asList(RoleManager,RoleAdmin,RoleUser, RoleAssocition);
        this.roleRepository.saveAll(roles);

     Set<Role> r1=new HashSet<Role>();

        r1.add(RoleAdmin);
        r1.add(RoleUser);
        Set<Role> r2=new HashSet<>();
        r2.add(RoleUser);
        Set<Role> r3=new HashSet<>();
        r3.add(RoleManager);
        Set<Role> r4=new HashSet<>();
        r4.add(RoleAssocition);




        this.userRepository.deleteAll();
        User Mahmoud = new User(1,"Mahmoud timoumi",passwordEncoder.encode("mahmoud123"),
                "Mahmoud","Timoumi","timoumi@gmail.com", User.Gender.MALE,"Student",
                1,"2145555", "Ariana Raoued", r2);

        User Admin = new User(2,"admin",passwordEncoder.encode("admin123"),
                "admin","admin","admin@gmail.com", User.Gender.MALE,"admin",
                1,"2145555", "Tunis ", r1);

        User Manager = new User(3,"manager",passwordEncoder.encode("manager123"),
                "manager","manager","manager@gmail.com", User.Gender.MALE,"manager",
                1,"2145555", "Ariana Raoued", r3);
        User Assosiation = new User(4,"assos",passwordEncoder.encode("assos123"),
                "assos","assos","assos@gmail.com", User.Gender.MALE,"assos",
                1,"2145555", "hell", r4);

        List<User> users = Arrays.asList(Mahmoud, Admin, Manager/*, Assosiation*/);

        this.userRepository.saveAll(users);

    }
}
