package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.Animal;
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
