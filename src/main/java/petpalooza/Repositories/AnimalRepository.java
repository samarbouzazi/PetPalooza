package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import petpalooza.Entities.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("Select a from Animal a where a.nameAnimal like %?1%")
    public List<Animal> searchByName(String nameAnimal);

    @Query("Select a from Animal a where a.race = ?1 ")
    public List<Animal> filterByRace(String race);

    @Query("Select a.race from Animal a group by a.race")
    public List<String> getAllRaces();

    @Query("Select a from Animal a where a.gender = ?1")
    public List<Animal> getByGender(String genderAnimal);
}
