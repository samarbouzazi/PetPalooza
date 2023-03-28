package petpalooza.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Animal;
import petpalooza.Services.AnimalService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("animal")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping("/")
    public List<Animal> getAll(){
        return this.animalService.getAllAnimals();
    }

    @PutMapping("/update")
    public Animal updateAnimal(@RequestBody Animal animal){
        return this.animalService.updateAnimal(animal);
    }

    @PostMapping("/add")
    public Animal addAnimal(@RequestBody Animal animal){
        return this.animalService.addAnimal(animal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimal(@PathVariable long id){
        this.animalService.deleteAnimal(id);
    }

    @GetMapping("/{id}")
    public Animal getById(@PathVariable long id){
        return this.animalService.getById(id);

    }
}
