package petpalooza.RestControllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Email;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.IEmailService;
import petpalooza.Services.IEvent;
import petpalooza.Services.userServices.IUser;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

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



    @GetMapping("/affev")
    public List<Event> retrieveallEvents(){
        return iEvent.retrieveallEvents();
    }


    //add event and affect to user
    @PostMapping ("/addev")
    public Event addEvent(@RequestBody Event event,Authorization authorization){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userRepository.findByUsername(userName).get();
        //Long id = user.getIdUser();
        event.setOwner(user);
        return iEvent.addeEvent(event);
    }

    @PutMapping("/updateev")
    public Event updateEvent(@RequestBody Event event){
        return iEvent.updateEvent(event);
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
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Email details) {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody Email details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }

    @GetMapping("/search")
    public List<Event> findByAnyParam(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) TypeEvent typeEvent,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datedebut,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date datefin,
            @RequestParam(required = false) Integer maxpart
    ){
        return iEvent.findAllsearch(
                title,  location,  description,  datedebut,  datefin,  maxpart,  typeEvent
        );
    }

    @GetMapping("/findstream")
    public List<Event> search(@RequestParam  String s){
        return iEvent.search(s);
    }


}