package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.ERole;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String username);


    List<User> findUserByRoles(ERole roleName);
}
