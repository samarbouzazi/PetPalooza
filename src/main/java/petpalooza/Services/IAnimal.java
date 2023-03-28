package petpalooza.Services;

import petpalooza.Entities.Animal;

import java.util.List;

public interface IAnimal {
    public Animal addAnimal(Animal animal);
    public Animal updateAnimal(Animal animal);
    public void deleteAnimal(long id);
    public List<Animal> getAllAnimals();
    public Animal getById (long id);
    


}
