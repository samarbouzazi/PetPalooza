package petpalooza.RestControllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Animal;
import petpalooza.Entities.JobOffer;
import petpalooza.Repositories.JobOffreRepository;
import petpalooza.Services.IJobOffre;
import petpalooza.Services.JobOffreService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data

public class JobOffreController {

    IJobOffre iJobOffre;
    JobOffreRepository jobOffreRepository;

    @GetMapping("/listeoffre")
    public List<JobOffer> getAll() {
        return iJobOffre.getAllJobOffers();
    }

    @PutMapping("/updateoffre")
    public JobOffer updateJobOffer(@RequestBody JobOffer jobOffer) {
        return iJobOffre.updateJobOffer(jobOffer);
    }

    @PostMapping("/addoffre")
    public JobOffer addJobOffer(@RequestBody JobOffer jobOffer) {
        return iJobOffre.addJobOffer(jobOffer);
    }

    @DeleteMapping("/deleteoffre/{id}")
    public void deleteJobOffer(@PathVariable long id) {
        iJobOffre.deleteJobOffer(id);
    }

    @GetMapping("/getoffre/{id}")
    public JobOffer getById(@PathVariable long id) {
        return iJobOffre.getById(id);


    }
    @GetMapping("/statjoboffre")
    public String  getJobOffreStat() {
        return iJobOffre.JobOffreStat();
    }
   @GetMapping("/getbyprice/{price}")
    public List<JobOffer>  getbyprice(@PathVariable float price)
    {
        return iJobOffre.search(price);
    }
    @GetMapping("/tri")
    public  List<JobOffer> tri(){
        return iJobOffre.findAllByPrice();
    }
    @GetMapping("/filter/{offretype}")
    public List<JobOffer> filterByOffretpe(@PathVariable String offretype ){

        return iJobOffre.filterByOffretype(offretype);
    }


}
