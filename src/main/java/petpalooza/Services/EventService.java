package petpalooza.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Repositories.EventRepository;

import java.util.List;

@Service
@Data
public class EventService implements IEvent{

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> retrieveallEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event addeEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deletEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> retrievealleventsforuser(User user) {
        return null;
    }

}
