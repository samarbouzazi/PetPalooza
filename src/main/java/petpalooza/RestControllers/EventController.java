package petpalooza.RestControllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Services.EventService;
import petpalooza.Services.IEvent;
import petpalooza.Services.IUser;

import java.util.List;

@RestController
@Data
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

    @PutMapping("/apdateev")
    public Event updateEvent(@RequestBody Event event){
        return iEvent.updateEvent(event);
    }

    @DeleteMapping("/delev/{id}")
    public void deleteev(@PathVariable("id") Long id){
        iEvent.deletEvent(id);
    }

}