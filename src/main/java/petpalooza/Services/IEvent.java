package petpalooza.Services;

import petpalooza.Entities.Event;

import java.util.List;

public interface IEvent {

    public List<Event> retrieveallEvents();
    public Event updateEvent(Event event);
    public Event addeEvent(Event event);
    public void deletEvent(Long id);
    public Event getById(long id);
    public Event participer(Long numEvent, Long idUser);
    public Event interesser(Long idEvent, Long idUser);



}
