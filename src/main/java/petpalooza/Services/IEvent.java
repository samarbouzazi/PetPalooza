package petpalooza.Services;

import org.apache.tomcat.util.http.parser.Authorization;
import petpalooza.DTO.CountType;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;

import java.util.Date;
import java.util.List;

public interface IEvent {

    public List<Event> retrieveallEvents();
    public Event updateEvent(Event event);
    public Event addeEvent(Event event);
    public void deletEvent(Long id);
    public Event getById(long id);
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




}
