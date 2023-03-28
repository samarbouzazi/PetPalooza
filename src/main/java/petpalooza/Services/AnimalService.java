package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Animal;
import petpalooza.Repositories.AnimalRepository;

import java.util.List;
@Service
public class AnimalService implements IAnimal{

    @Autowired
    AnimalRepository animalRepository;
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
}
