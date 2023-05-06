package petpalooza.Services;

import org.springframework.data.domain.Page;
import petpalooza.Entities.Animal;
import petpalooza.Entities.RatingAnimal;

import java.util.List;

public interface IAnimal {
    public Animal addAnimal(Animal animal);
    public Animal updateAnimal(Animal animal, Long id);
    public void deleteAnimal(long id);
    public List<Animal> getAllAnimals();
    public Animal getById (long id);
    public List<Animal> searchAnimal(String nameAnimal);
    public List<Animal> filterByRace (String raceAnimal);
    public List<String> getAllRaces ();
    public List<Animal> getByGender(String genderAnimal);
    public List<RatingAnimal> getlikes(Long idAnimal);
    public List<RatingAnimal> getdislikes(Long idAnimal);
    public RatingAnimal likeUserToAnimal(long idAnimal, long idUser);
    public RatingAnimal rate(RatingAnimal ratingAnimal);
    public Animal setInterested(long idAnimal, long idUser);
    public Page<Animal> findPage(int pageNumber);
    public List<Object[]> getAnimalRaceStats();
    public int countInterestedUsers(long idAnimal);

}
