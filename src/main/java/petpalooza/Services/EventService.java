package petpalooza.Services;

import lombok.Data;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import petpalooza.DTO.CountType;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;
import petpalooza.Repositories.EventRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.userServices.IUser;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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

//    @Override
//    public Event updateEvent(Event event) {
//        return eventRepository.save(event);
//    }


    @Override
    public Event updateEvent(Event event,Long id){


    Event existingEvent = eventRepository.findById(id).orElse(null);
        existingEvent.setTitre(event.getTitre());
        existingEvent.setType(event.getType());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setLocation(event.getLocation());
        existingEvent.setDateDebut(event.getDateDebut());
        existingEvent.setDateFin(event.getDateFin());
        existingEvent.setMaxParticipants(event.getMaxParticipants());

        return eventRepository.save(existingEvent);
}
    @Override
    public Event retrieveEvent(Long id) {
        return this.eventRepository.findById(id).get();
    }



//    @Override
//    public Event addeEvent(Event event) {
//
//        return eventRepository.save(event);
//    }

    @Override
    public Event addeEvent(Event event){
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


    //////stat//////////
    @Override
    public List<CountType> statistique()
    {
        return this.eventRepository.statistque();
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
    public List<Event> search(String s, Date startDate, Date endDate, Integer maxParticipants) {
        return eventRepository.findAll().stream()
                .filter(event -> event.getTitre() != null || event.getTitre().equals(s))
                .filter(event -> event.getDescription() != null || event.getDescription().contains(s))
                .filter(event -> event.getLocation() != null || event.getLocation().contains(s))
                .filter(event -> event.getDateDebut() == null || event.getDateDebut().after(startDate))
                .filter(event -> event.getDateFin() == null || event.getDateFin().before(endDate))
                .filter(event -> event.getMaxParticipants() == null || event.getMaxParticipants() == maxParticipants)
                .collect(Collectors.toList());
    }


    @Override
    public List<Event> searchh(String s) {
//        Stream<Event> stream = eventRepository.findAll().stream();
//        List<Event> collect = stream.filter(e -> e.getTitre().equals(s)).collect(Collectors.toList());
//        collect.forEach(System.out::println);
        return eventRepository.findAll().stream().filter(event -> event.getTitre()!=null )
                .filter(event -> event.getTitre().contains(s)  ).collect(Collectors.toList());
        //return eventRepository.findAll();
        //eventRepository.findAll().stream().filter(event -> event.getTitre().equals("Don VacPets")).forEach(System.out::println);


    }


//    public List<Event> search(String s) {
//
//        return eventRepository.findAll().stream().filter(event -> event.getTitre()!=null
//                        || event.getDescription()!=null
//                        || event.getLocation()!=null
//                        || event.getType()!=null)
//                .filter(event -> event.getTitre().equals(s)
//                        || event.getType().equals(s)
//                        || event.getDescription().equals(s)
//                        || event.getLocation().equals(s)
//                ).collect(Collectors.toList());
//        //        Stream<Event> stream = eventRepository.findAll().stream();
//        //        List<Event> collect = stream.filter(e -> e.getTitre().equals(s)).collect(Collectors.toList());
//        //        collect.forEach(System.out::println);
//       //eventRepository.findAll().stream().filter(event -> event.getTitre().equals("Don VacPets")).forEach(System.out::println);
//
//
//    }


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