package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
