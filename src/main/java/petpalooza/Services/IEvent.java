package petpalooza.Services;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.web.multipart.MultipartFile;
import petpalooza.DTO.CountType;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IEvent {

    public List<Event> retrieveallEvents();
    public Event updateEvent(Event event,Long id);
    public Event addeEvent(Event event)
            throws IOException
            ;
    public void deletEvent(Long id);
    public Event getById(long id);
    public Event retrieveEvent(Long id);
    public Event participer(Long numEvent, Long idUser);
    public Event interesser(Long idEvent, Long idUser);
    public List<Event> getEventsByParticipants();

    public List<Event> findAllsearch(
            String title, String location, String description, Date datedebut, Date datefin,Integer maxpart, TypeEvent typeEvent
    );

    public List<Event> search (
            String s, Date startDate, Date endDate, Integer maxParticipants
    );

    public List<Event> searchh (
            String s
    );


    public List<CountType> statistique();


    public List<Event> search(String keyword);


    public List<Event> searchEvents(String titre, String type, Integer maxParticipants, String location, String dateDebut, String dateFin);

}
