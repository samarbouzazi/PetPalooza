package petpalooza.RestControllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Event;
import petpalooza.Services.EventService;
import petpalooza.Services.IEvent;

import java.util.List;

@RestController
@Data
public class EventController {
    @Autowired
    IEvent iEvent;
    @GetMapping("/affev")
    public List<Event> retrieveallEvents(){
        return iEvent.retrieveallEvents();
    }

    @PostMapping ("/addev")
    public Event addEvent(@RequestBody Event event){
        return iEvent.addeEvent(event);
    }
}
