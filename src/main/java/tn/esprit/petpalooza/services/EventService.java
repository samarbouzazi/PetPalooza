package tn.esprit.petpalooza.services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.petpalooza.entities.Event;
import tn.esprit.petpalooza.repositories.EventRepository;

import java.util.List;
@Service
public class EventService implements IEvent{

    @Autowired
    EventRepository eventRepository;
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

}
