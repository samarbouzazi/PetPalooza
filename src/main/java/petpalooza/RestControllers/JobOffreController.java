package petpalooza.RestControllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.JobOffer;
import petpalooza.Repositories.JobOffreRepository;
import petpalooza.Services.IJobOffre;
import petpalooza.Services.PaiementJobService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data
@RequestMapping("/JobOffre")

public class JobOffreController {

    IJobOffre iJobOffre;
    JobOffreRepository jobOffreRepository;

    @GetMapping("/listeoffre")
    public List<JobOffer> getAll() {
        return iJobOffre.getAllJobOffers();
    }

   /* @PutMapping("/updateoffre")
    public JobOffer updateJobOffer(@RequestBody JobOffer jobOffer) {
        return iJobOffre.updateJobOffer(jobOffer);
    }*/
    @PutMapping("/updateoffre/{idJob}")
    public JobOffer updateEvent(@RequestBody JobOffer jobOffer, @PathVariable("idJob") Long idJob){
        return iJobOffre.updateJobOffer(jobOffer,idJob);
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
    @PutMapping("/interested/{idJob}")
    public String interestedoffre(@PathVariable ("idJob") Long idjob)
    {
        iJobOffre.interestOffre( idjob);
        return "id has been job";
    }
    @PostMapping("/interesser/{idJob}/{idUser}")
    public JobOffer intersted(@PathVariable("idJob") Long idJob, @PathVariable("idUser") Long idUser){
        return iJobOffre.interesser(idJob,idUser);
    }
    ////PAIEMENT/////

    @PostMapping("/pay")
    public ResponseEntity<String> charge(@RequestParam("token") String token, @RequestParam("amount") int amount) {
        try {
            Charge charge = PaiementJobService.chargeCreditCard(token, amount);
            return ResponseEntity.ok(" we're glad to inform you that your payment has been successful! ");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}