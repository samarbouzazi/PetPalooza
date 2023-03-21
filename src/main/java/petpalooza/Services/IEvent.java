package petpalooza.Services;

import petpalooza.Entities.Event;
import petpalooza.Entities.User;

import java.util.List;

public interface IEvent {

    public List<Event> retrieveallEvents();
    public Event updateEvent(Event event);
    public Event addeEvent(Event event);
    public void deletEvent(Long id);

    public List<Event> retrievealleventsforuser(User user);

}
