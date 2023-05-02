package petpalooza.RestControllers;

import lombok.Data;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import petpalooza.DTO.CountType;
import petpalooza.Entities.*;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.IEmailService;
import petpalooza.Services.IEvent;
import petpalooza.utils.ExportpdfService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequestMapping("admin")
@CrossOrigin

public class EventController {

    @Autowired
    IEvent iEvent;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private final IEmailService emailService;

    @Autowired
    ExportpdfService export;




    @GetMapping("/affev")
    public List<Event> retrieveallEvents(){
        return iEvent.retrieveallEvents();
    }


//    add event and affect to user
    @PostMapping ("/addev")
    public Event addEvent(@ModelAttribute Event event,Authorization authorization, @RequestParam("image") MultipartFile image)throws IOException {
        //Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        //String userName = loggedInUser.getName();
        //User user = userRepository.findByUsername(userName).get();
        //Long id = user.getIdUser();
        //event.setOwner(user);
        emailService.sendSimpleMail(event);
        return iEvent.addeEvent(event,image);
    }


//    @PostMapping ("/addev")
//    public Event addEvent(@RequestBody Event event, @RequestParam("imageEvent") MultipartFile imageEvent)throws IOException {
//        ImageEvent imageEvent1=new ImageEvent();
//        imageEvent1.setContent(imageEvent.getBytes());
//        imageEvent1.setName(imageEvent.getOriginalFilename());
//        event.setImageEvent(this.imageEventRepository.save(imageEvent1));
//        emailService.sendSimpleMail(event);
//        return iEvent.addeEvent(event, imageEvent);
//    }

    @PutMapping("/updateev/{id}")
    public Event updateEvent(@RequestBody Event event, @PathVariable("id") Long id){
        return iEvent.updateEvent(event,id);
    }
    @GetMapping("/retrieve-Event/{id}")
    @ResponseBody
    private Optional<Event> getEvent(@PathVariable("id") Long id)
    {
        return Optional.ofNullable(this.iEvent.retrieveEvent(id));
    }
    @DeleteMapping("/delev/{id}")
    public void deleteev(@PathVariable("id") Long id){
        iEvent.deletEvent(id);
    }


    @PostMapping("/participer/{numEvent}/{idUser}")
    public Event participer(@PathVariable("numEvent") Long numEvent, @PathVariable("idUser") Long idUser){
        return iEvent.participer(numEvent,idUser);
    }
    @PostMapping("/interesser/{numEvent}/{idUser}")
    public Event intersted(@PathVariable("numEvent") Long numEvent, @PathVariable("idUser") Long idUser){
        return iEvent.interesser(numEvent,idUser);
    }

    @PostMapping("/tri")
    public List<Event> sortEventsByParticipants(){
        return iEvent.getEventsByParticipants();
    }



    // Sending a simple Email
//    @PostMapping("/sendMail")
//    public String sendMail(@RequestBody Email details) {
//        String status = emailService.sendSimpleMail(details);
//        return status;
//    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody Email details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }

//    @GetMapping("/search")
//    public List<Event> findByAnyParam(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) TypeEvent typeEvent,
//            @RequestParam(required = false) String location,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedebut,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin,
//            @RequestParam(required = false) Integer maxpart
//    ){
//        return iEvent.findAllsearch(
//                title,  location,  description,  datedebut,  datefin,  maxpart,  typeEvent
//        );
//    }

//    @GetMapping("/findstream")
//    public List<Event> search(@RequestParam  String s){
//        return iEvent.search(s);
//    }


    @GetMapping("/search")
    public List<Event> searchEvents(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer maxParticipants
    ) {
        return iEvent.search(
                searchQuery, startDate, endDate,  maxParticipants
        );
    }


    @GetMapping("/findstream")
    public List<Event> searchh(@RequestParam  String s){
        return iEvent.searchh(s);
    }



    @GetMapping("/statistique")

    List<CountType> statistique(){
        return iEvent.statistique();
    }

    @GetMapping("/exportpdf")
    public ResponseEntity<InputStreamResource> exportPdf() {
        List<Event> events = (List<Event>) iEvent.retrieveallEvents();
        ByteArrayInputStream bais = export.eventExport(events);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline;filename=event.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }




}