package tn.esprit.petpalooza.services;

import tn.esprit.petpalooza.entities.Event;

import java.util.List;

public interface IEvent {
    List<Event> retrieveAllEvents();
    Event addEvent(Event event);
    void deleteEvent(Long id);
    Event updateEvent(Event event);

}
