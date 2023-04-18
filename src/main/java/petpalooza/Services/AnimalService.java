package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Animal;
import petpalooza.Entities.RatingAnimal;
import petpalooza.Entities.User;
import petpalooza.Repositories.AnimalRepository;
import petpalooza.Repositories.RatingAnimalRepository;
import petpalooza.Repositories.UserRepository;

import java.util.List;
@Service
public class AnimalService implements IAnimal{

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    RatingAnimalRepository ratingAnimalRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Animal addAnimal(Animal animal) {
        return this.animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return this.animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(long id) {
        this.animalRepository.deleteById(id);
    }

    @Override
    public List<Animal> getAllAnimals() {

        return this.animalRepository.findAll();

    }

    @Override
    public Animal getById(long id) {

        return this.animalRepository.findById(id).get();

    }

    @Override
    public List<Animal> searchAnimal(String nameAnimal) {
        return this.animalRepository.searchByName(nameAnimal);
    }

    @Override
    public List<Animal> filterByRace(String raceAnimal) {
        return this.animalRepository.filterByRace(raceAnimal);
    }

    @Override
    public List<String> getAllRaces() {
        return this.animalRepository.getAllRaces();
    }

    @Override
    public List<Animal> getByGender(String genderAnimal) {
        return this.animalRepository.getByGender(genderAnimal);
    }

    @Override
    public List<RatingAnimal> getlikes(Long idAnimal) {
        return this.ratingAnimalRepository.likesAnimal(idAnimal);
    }

    @Override
    public List<RatingAnimal> getdislikes(Long idAnimal) {
        return this.ratingAnimalRepository.dislikesAnimal(idAnimal);
    }

    @Override
    public RatingAnimal likeUserToAnimal(long idAnimal, long idUser) {
        return this.ratingAnimalRepository.likeUserToAnimal(idAnimal, idUser);
    }

    @Override
    public RatingAnimal rate (RatingAnimal ratingAnimal) {
        return this.ratingAnimalRepository.save(ratingAnimal);
    }

    @Override
    public Animal setInterested(long idAnimal, long idUser) {
        User user = userRepository.findById(idUser).get();
        Animal animal = animalRepository.findById(idAnimal).get();
        for (User u : animal.getInterestedUsers()
             ) {
            if (u.equals(user)){
                return null;
            }
        }
        user.getInterestedAnimals().add(animal);
        animal.getInterestedUsers().add(user);
        userRepository.save(user);
        return animalRepository.save(animal);

    }


}
