package petpalooza.RestControllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.JobOffer;
import petpalooza.Services.IJobOffre;
import petpalooza.Services.JobOffreService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data

public class JobOffreController {

    IJobOffre iJobOffre;

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

    @GetMapping("/getoffre{id}")
    public JobOffer getById(@PathVariable long id) {
        return iJobOffre.getById(id);


    }

}