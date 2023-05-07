package petpalooza.RestControllers;

import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petpalooza.Entities.Event;

import petpalooza.Entities.JobOffer;
import petpalooza.Entities.User;
import petpalooza.Repositories.JobOffreRepository;
import petpalooza.Services.IJobOffre;

import petpalooza.Services.PaiementJobService;
import petpalooza.Services.userServices.UserService;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@Data
@RequestMapping("/JobOffre")

public class JobOffreController {

    @Autowired
    IJobOffre iJobOffre;

    @Autowired
    JobOffreRepository jobOffreRepository;

    UserService userService;

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
    public JobOffer addJobOffer(
        @RequestParam("offre") String job,
                @RequestParam("image") MultipartFile file
    ) throws IOException {
              JobOffer jobOffer = new Gson().fromJson(job, JobOffer.class);


            String image=file.getOriginalFilename();
            String path="C://xampp/htdocs/img";

            byte[] bytes = image.getBytes();
            int image2=bytes.toString().hashCode();
            Files.copy(file.getInputStream(), Paths.get(path+ File.separator+image2+".jpg"));

            jobOffer.setImage(""+image2);

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
    ////PAIEMENT/////(front)

    @PostMapping("/pay")
    public ResponseEntity<String> charge(@RequestParam("token") String token, @RequestParam("amount") int amount) {
        try {
            Charge charge = PaiementJobService.chargeCreditCard(token, amount);
            return ResponseEntity.ok(" we're glad to inform you that your payment has been successful! ");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/contrat/{iduser1}/{iduser2}/{idjoboffre}")
    public void ContratJob(@PathVariable Long iduser1, @PathVariable Long iduser2, @PathVariable Long idjoboffre, HttpServletResponse response) throws IOException, DocumentException {
        User user1 = userService.findUserByID(iduser1);
        User user2 = userService.findUserByID(iduser2);
        JobOffer jobOffer = iJobOffre.getById(idjoboffre);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(font);
        paragraph.add("User 1: " + user1.getFirstName() + "\n");
        paragraph.add("User 2: " + user2.getFirstName() + "\n");
        paragraph.add("Job Offer Title: " + jobOffer.getTitle() + "\n");
        paragraph.add("Job Offer Price: " + jobOffer.getPrice() + "\n");
        document.add(paragraph);
        document.close();

        response.setHeader("Content-Disposition", "attachment; filename=contrat.pdf");
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        ServletOutputStream outputStream = response.getOutputStream();
        baos.writeTo(outputStream);
        outputStream.flush();
    }
    @GetMapping("/search")//hedhi supposed post :) bech tab3eth request bech tsearchi bil attribut te3ik
    public List<JobOffer> searchJobOffer(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String offretype,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) String beginnigDate,
            @RequestParam(required = false) String endDate) {

        // Call your service method that returns the list of events filtered by the search query
        List<JobOffer> jobOffers = iJobOffre.searchJobOffer(title, offretype, price, localisation, beginnigDate, endDate);

        return jobOffers;
    }




}