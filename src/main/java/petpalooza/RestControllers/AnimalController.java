package petpalooza.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import petpalooza.Entities.Animal;
import petpalooza.Entities.RatingAnimal;
import petpalooza.Entities.User;
import petpalooza.Repositories.RatingAnimalRepository;
import petpalooza.Services.AnimalService;
import petpalooza.security.payload.response.MessageResponse;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/animal")
@CrossOrigin
public class AnimalController {
    @Autowired
    AnimalService animalService;
    @Autowired
    RatingAnimalRepository ratingAnimalRepository;

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

    @GetMapping("/search/{name}")
    public List<Animal> searchByName(@PathVariable String name){

        return this.animalService.searchAnimal(name);
    }

    @GetMapping("/filter/{race}")
    public List<Animal> filterByRace(@PathVariable String race){

        return this.animalService.filterByRace(race);
    }

    @GetMapping("/races")
    public List<String> getAllRaces(){

        return this.animalService.getAllRaces();
    }

    @GetMapping("/sexe/{genderAnimal}")
    public List<Animal> getByGender(@PathVariable String genderAnimal){
        return this.animalService.getByGender(genderAnimal);
    }

    @PostMapping("/rate")
    public RatingAnimal rate(@RequestBody RatingAnimal ratingAnimal){
        return this.animalService.rate(ratingAnimal);
    }

    @GetMapping("/getlikes/{idAnimal}")
    public List<RatingAnimal> getlikes(@PathVariable long idAnimal){
        return this.animalService.getlikes(idAnimal);

    }

    @GetMapping("/getdislikes/{idAnimal}")
    public List<RatingAnimal> getdislikes(@PathVariable long idAnimal){
        return this.animalService.getdislikes(idAnimal);
    }

    @GetMapping("/getrate/{idAnimal}/{idUser}")
    public RatingAnimal likeUserToAnimal(@PathVariable long idAnimal,@PathVariable long idUser){
        return this.animalService.likeUserToAnimal(idAnimal,idUser);
    }

    @GetMapping("/interested/{idAnimal}/{idUser}")
    public Animal interestedAnimal(@PathVariable long idAnimal,@PathVariable long idUser){
        return this.animalService.setInterested(idAnimal,idUser);
    }
    //hey
    @GetMapping ("/nbrLikesOfAnimal/{idAnimal}")
    public ResponseEntity<?> getLikes(@PathVariable("idAnimal") long idAnimal){
        int s = ratingAnimalRepository.nbrLikes(idAnimal);
        return ResponseEntity.ok(new MessageResponse("\n the number of likes is "+ s + "!!"));

    }

    @GetMapping ("/nbrDisLikesOfAnimal/{idAnimal}")
    public ResponseEntity<?> getDisLikes(@PathVariable("idAnimal") long idAnimal){
        int i = ratingAnimalRepository.nbrDisLikes(idAnimal);
        return ResponseEntity.ok(new MessageResponse("\n the number of dislikes is "+ i+ "!!"));

    }
    @GetMapping("pageAnimal/{pageNumber}")

    public  List<Animal> getOnePage(@PathVariable("pageNumber") int currentPage){
        Page<Animal> page = animalService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Animal> animals = page.getContent();

        System.out.println("\n  \"the total number of pages "+  totalPages +
                "//               \"\\n  and the TotalItems is  "  + totalItems +  "\n and the content of this page is  " + animals   );

        return animals;

    }
}

