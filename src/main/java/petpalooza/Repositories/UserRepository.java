package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
