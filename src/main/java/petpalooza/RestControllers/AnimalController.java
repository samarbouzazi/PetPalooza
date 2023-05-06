package petpalooza.RestControllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.hql.internal.ast.tree.AbstractNullnessCheckNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import petpalooza.Entities.Animal;
import petpalooza.Entities.ImageAnimal;
import petpalooza.Entities.RatingAnimal;
import com.google.gson.Gson;
import petpalooza.Entities.User;
import petpalooza.Repositories.AnimalRepository;
import petpalooza.Repositories.ImageAnimalRepository;
import petpalooza.Repositories.RatingAnimalRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.AnimalService;
import petpalooza.security.payload.response.MessageResponse;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/animal")
@Slf4j
@CrossOrigin
public class AnimalController {
    @Autowired
    ImageAnimalRepository imageAnimalRepository;
    @Autowired
    AnimalService animalService;
    @Autowired
    RatingAnimalRepository ratingAnimalRepository;
    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/")
    public List<Animal> getAll(){
        return this.animalService.getAllAnimals();
    }

    @PutMapping("/update/{id}")
    public Animal updateAnimal(@RequestBody Animal animal, @PathVariable("id") Long id){
        return this.animalService.updateAnimal(animal, id);
    }

    @PostMapping("/add")
    public Animal addAnimal(
            @RequestParam("animal") String anim,
            @RequestParam("image")MultipartFile file
    ) throws IOException {
        Animal anima = new Gson().fromJson(anim, Animal.class);


        String image=file.getOriginalFilename();
        String path="C://wamp64/www/img";

        byte[] bytes = image.getBytes();
        int image2=bytes.toString().hashCode();
        Files.copy(file.getInputStream(), Paths.get(path+ File.separator+image2+".jpg"));

        anima.setImage(""+image2);

        return this.animalService.addAnimal(anima);
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



    @GetMapping("/getlikes/{idAnimal}")
    public List<RatingAnimal> getlikes(@PathVariable long idAnimal){
        return this.animalService.getlikes(idAnimal);

    }

    @GetMapping("/getdislikes/{idAnimal}")
    public List<RatingAnimal> getdislikes(@PathVariable long idAnimal){
        return this.animalService.getdislikes(idAnimal);
    }

    @PostMapping("/addlike/{idAnimal}/{idUser}")
    public RatingAnimal addlike(@RequestBody RatingAnimal ratingAnimal,@PathVariable long idAnimal,@PathVariable long idUser){

        if(animalService.likeUserToAnimal(idAnimal,idUser) != null && animalService.likeUserToAnimal(idAnimal,idUser).isLiked()){
            return  null;
        }
        else if(animalService.likeUserToAnimal(idAnimal,idUser) != null && !animalService.likeUserToAnimal(idAnimal,idUser).isLiked()){
            animalService.deleteRatinganimal(animalService.likeUserToAnimal(idAnimal,idUser).getIdRating());


            return animalService.rate(ratingAnimal);
        }
        else {
            return animalService.rate(ratingAnimal);
        }

    }

    @PostMapping("/addDislike/{idAnimal}/{idUser}")
    public RatingAnimal addDislike(@RequestBody RatingAnimal ratingAnimal,@PathVariable long idAnimal,@PathVariable long idUser){

        if(animalService.likeUserToAnimal(idAnimal,idUser) != null && !animalService.likeUserToAnimal(idAnimal,idUser).isLiked()){
            return  null;
        }
        else if(animalService.likeUserToAnimal(idAnimal,idUser) != null && animalService.likeUserToAnimal(idAnimal,idUser).isLiked()){
            animalService.deleteRatinganimal(animalService.likeUserToAnimal(idAnimal,idUser).getIdRating());
            return animalService.rate(ratingAnimal);
        }
        else {
            return animalService.rate(ratingAnimal);
        }

    }

    @PostMapping("/rate")
    public RatingAnimal rate(@RequestBody RatingAnimal ratingAnimal,@PathVariable long idAnimal,@PathVariable long idUser){
        if(this.animalService.likeUserToAnimal(idAnimal,idUser).isLiked()==ratingAnimal.isLiked())
            return  null;
        if(this.animalService.likeUserToAnimal(idAnimal,idUser).isLiked()==!ratingAnimal.isLiked()){
            RatingAnimal ratingAnimal1 = ratingAnimalRepository.findById(ratingAnimal.getIdRating()).get();
            //  ratingAnimal1.setLiked();
        }


        return this.animalService.rate(ratingAnimal);



    }

    @GetMapping("/interested/{idAnimal}/{idUser}")
    public Animal interestedAnimal(@PathVariable long idAnimal,@PathVariable long idUser){
        return this.animalService.setInterested(idAnimal,idUser);
    }
    //hey
    @GetMapping ("/nbrLikesOfAnimal/{idAnimal}")
    public int getLikes(@PathVariable long idAnimal){
        int s = ratingAnimalRepository.nbrLikes(idAnimal);
        return  s;

    }

    @GetMapping ("/nbrDisLikesOfAnimal/{idAnimal}")
    public int getDisLikes(@PathVariable("idAnimal") long idAnimal){
        int i = ratingAnimalRepository.nbrDisLikes(idAnimal);
        return i;

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
   /* @GetMapping("/animals/sorted-by-likes")
    public ResponseEntity<?> getAnimalsSortedByLikes() {
        List<Animal> animals = animalRepository.findAll();
        animals.sort(Comparator.comparingInt(Animal::getL).reversed());
        return ResponseEntity.ok(animals);
    }*/

    @GetMapping("/sortedByLikes")
    public ResponseEntity<?> getAnimalsSortedByLikes() {
        List<Animal> animals = animalRepository.findAll();
        animals.forEach(a -> a.setLikes(ratingAnimalRepository.nbrLikes(a.getIdAnimal())));
        animals.sort(Comparator.comparingInt(Animal::getLikes).reversed());
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/sortedByDislikes")
    public ResponseEntity<?> getAnimalsSortedByDisLikes() {
        List<Animal> animals = animalRepository.findAll();
        animals.forEach(a -> a.setDislikes(ratingAnimalRepository.nbrDisLikes(a.getIdAnimal())));
        animals.sort(Comparator.comparingInt(Animal::getDislikes).reversed());
        return ResponseEntity.ok(animals);
    }
    @GetMapping("/stats/race")
    public ResponseEntity<List<Object[]>> getAnimalRaceStats() {
        List<Object[]> results = animalService.getAnimalRaceStats();
        return ResponseEntity.ok(results);
    }
    @GetMapping("/stats/gender")
    public ResponseEntity<List<Object[]>> getAnimalGenderStats() {
        List<Object[]> results = animalService.getAnimalGenderStats();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/interestedUsers/count/{idAnimal}")
    public int countInterestedUsers(@PathVariable long idAnimal) {
        return animalService.countInterestedUsers(idAnimal);
    }
    @GetMapping("/animals/excel")
    public void exportAnimalsToExcel(HttpServletResponse response) throws IOException {
        // Créez un nouveau classeur Excel
        Workbook workbook = new XSSFWorkbook();

        // Créez une nouvelle feuille de calcul
        Sheet sheet = workbook.createSheet("Animals");

        // Ajoutez une ligne d'en-tête avec les noms des colonnes
        Row headerRow = sheet.createRow(0);
        //   headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Birth Date");
        headerRow.createCell(2).setCellValue("Race");
        headerRow.createCell(3).setCellValue("Description");
        headerRow.createCell(4).setCellValue("Gender");
        headerRow.createCell(5).setCellValue("Image");
        //    headerRow.createCell(7).setCellValue("Likes");

        // Récupérez les données sur les animaux à partir de la base de données
        List<Animal> animals = animalRepository.findAll();

        // Remplissez les données dans les lignes suivantes
        int rowNum = 1;
        for (Animal animal : animals) {
            Row row = sheet.createRow(rowNum++);
            //  row.createCell(0).setCellValue(animal.getIdAnimal());
            row.createCell(0).setCellValue(animal.getNameAnimal());
            //   row.createCell(1).setCellValue( (String)animal.getBirthDate());
            //  row.createCell(2).setCellValue((String)animal.getBirthDate());
            row.createCell(1).setCellValue(animal.getBirthDate().toString());
            row.createCell(2).setCellValue(animal.getRace());
            row.createCell(3).setCellValue(animal.getDescription());
            row.createCell(4).setCellValue(animal.getGender());
            //row.createCell(5).setCellValue(animal.getImage());
            //  row.createCell(7).setCellValue(animal.getLikes());
        }

        // Configurez la réponse HTTP pour renvoyer le fichier Excel en tant que réponse
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=animals.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}

