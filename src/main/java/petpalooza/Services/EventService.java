package petpalooza.Services;

import lombok.Data;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;
import petpalooza.Repositories.EventRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.userServices.IUser;

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Data
public class EventService implements IEvent {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
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

    @Override
    public List<Event> getEventsByParticipants() {
        return eventRepository.eventsorted();
    }

    @Override
    public List<Event> findAllsearch(
            String title, String location, String description, Date datedebut, Date datefin, Integer maxpart,TypeEvent typeEvent
    ) {
        return eventRepository.searchEventsByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrMaxParticipantsOrType(
                title,  typeEvent,  description, location,  datedebut,  datefin,  maxpart
        );
    }

    @Override
    public List<Event> search(String s) {
//        Stream<Event> stream = eventRepository.findAll().stream();
//        List<Event> collect = stream.filter(e -> e.getTitre().equals(s)).collect(Collectors.toList());
//        collect.forEach(System.out::println);
        return eventRepository.findAll().stream().filter(event -> event.getTitre()!=null ).filter(event -> event.getTitre().equals(s)  ).collect(Collectors.toList());
       //return eventRepository.findAll();
       //eventRepository.findAll().stream().filter(event -> event.getTitre().equals("Don VacPets")).forEach(System.out::println);


    }


//    searchUsers() {
//        if (this.searchTerm) {
//            console.log(this.searchTerm);
//            this.filteredUsers = this.users.filter((user) => {
//            return Object.values(user).some((value) => {
//            if (typeof value === 'string' && value.includes('+')) {
//                value = value.replace(/\D/g, ''); // remove all non-digit characters
//                if (value.toString().includes(this.searchTerm.toString())) {
//                    return true;
//                }
//            } else if (typeof value === 'string' |
//                    onInputChange(event: any) {
//                this.searchTerm = event.target.value;
//                this.searchUsers();
//            }


}