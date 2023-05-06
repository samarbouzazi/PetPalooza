package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Animal updateAnimal(Animal animal, Long id) {
        Animal existingAnimal = animalRepository.findById(id).orElse(null);
        existingAnimal.setNameAnimal(animal.getNameAnimal());
        existingAnimal.setBirthDate(animal.getBirthDate());
        existingAnimal.setRace(animal.getRace());
        existingAnimal.setDescription(animal.getDescription());
        existingAnimal.setGender(animal.getGender());
        return this.animalRepository.save(existingAnimal);
    }

    @Override
    public void deleteAnimal(long id) {
        this.animalRepository.deleteById(id);
    }

    @Override
    public List<Animal> getAllAnimals() {

        return animalRepository.findAll();

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


    public void deleteRatinganimal(long id){
        ratingAnimalRepository.deleteById(id);
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

    @Override
    public Page<Animal> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,6);
        return animalRepository.findAll(pageable);
    }

    @Override
    public List<Object[]> getAnimalRaceStats() {
        List<Object[]> results = animalRepository.getAnimalRaceStats();
        return results;
    }
    public List<Object[]> getAnimalGenderStats() {
        List<Object[]> results = animalRepository.getAnimalGenderStats();
        return results;
    }
    @Override
    public int countInterestedUsers(long idAnimal) {
        return animalRepository.countInterestedUsers(idAnimal);
    }





}
