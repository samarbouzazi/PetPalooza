package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.ERole;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   //list is a collection that can hold multiple objects of the same type,
    // while Optional is a container object that may or may not contain a value of a specific type.
//used when you want to represent the absence of a value. This can be useful when you're working with methods that may or may not return a value
     User findByUsername(String username);
    Boolean existsByUsername(String username);


    Boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByResetPasswordToken( String tocken);




   ///////////

//    List<User> findByRoleName(ERole name);


}
