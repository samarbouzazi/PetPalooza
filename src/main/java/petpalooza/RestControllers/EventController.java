package petpalooza.RestControllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Email;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Services.IEmailService;
import petpalooza.Services.IEvent;
import petpalooza.Services.userServices.IUser;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Data
@RequestMapping("admin")
@CrossOrigin
public class EventController {
    @Autowired
    IEvent iEvent;
    IUser iUser;

    @GetMapping("/affev")
    public List<Event> retrieveallEvents(){
        return iEvent.retrieveallEvents();
    }

    @PostMapping ("/addev")
    public Event addEvent(@RequestBody Event event){
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

    private final IEmailService emailService;

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


}