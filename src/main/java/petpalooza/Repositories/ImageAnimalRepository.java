package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.ImageAnimal;

@Repository
public interface ImageAnimalRepository extends JpaRepository<ImageAnimal, Long> {

}
