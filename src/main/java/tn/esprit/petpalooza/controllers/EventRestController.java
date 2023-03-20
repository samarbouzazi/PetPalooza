package tn.esprit.petpalooza.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.petpalooza.entities.Event;
import tn.esprit.petpalooza.services.IEvent;

import java.util.List;

@RestController
public class EventRestController {

    @Autowired
    IEvent iEvent;


    @PostMapping("/addev")
    public Event addAbonnement(@RequestBody Event event)
    {
        return iEvent.addEvent(event);
    }

    @GetMapping("/affev")
    public List<Event> retrieveAllEvents(){
        return iEvent.retrieveAllEvents();
    }

    @PutMapping("/updateev")
    public Event updateEvent(@RequestBody Event event){
        return iEvent.updateEvent(event);
    }

    @DeleteMapping("/deleteev/{id}")
    public void deleteEvent(@PathVariable("id") Long id){
        iEvent.deleteEvent(id);
    }

}
