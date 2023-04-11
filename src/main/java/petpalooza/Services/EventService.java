package petpalooza.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Repositories.EventRepository;
import petpalooza.Repositories.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@Data
public class EventService implements IEvent {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    IUser iUser;

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
    public Event getById(long id) {

        return eventRepository.findById(id).get();

    }

    @Override
    public Event participer(Long numEvent, Long idUser) {
        User user = userRepository.findById(idUser).get();
        Event event =eventRepository.findById(numEvent).get();

        for (User u:event.getParticipants()) {
            if (u.equals(user)){
                return null;
            }

        }
        event.getParticipants().add(user);
        user.getEvents().add(event);
        eventRepository.save(event);
        userRepository.save(user);
        return event;
    }

    @Override
    public Event interesser(Long idEvent, Long idUser) {
        User user = userRepository.findById(idUser).get();
        Event event =eventRepository.findById(idEvent).get();

        for (User u:event.getInterestedUsers()) {
            if (u.equals(user)){
                return null;
            }

        }
        event.getInterestedUsers().add(user);
        user.getEventInterested().add(event);
        eventRepository.save(event);
        userRepository.save(user);
        return event;

    }

}