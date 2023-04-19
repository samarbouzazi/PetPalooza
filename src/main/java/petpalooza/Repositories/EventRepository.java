package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByOrderByParticipantsDesc();

    //List<Event> findAllByOrderByParticipantsDesc();
    List<Event> findAllByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrMaxParticipantsOrType(
            String title, String location, String description, Date datedebut, Date datefin, Integer maxpart, TypeEvent typeEvent
    );

    @Query("select e from Event e where e.titre=?1 or e.type=?1 or e.description=?1 or e.location=?1 or e.dateDebut =?1 or e.dateFin=?1 or e.maxParticipants=?1 ")
    List<Event> searchEventsByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrMaxParticipantsOrType(
            String title, TypeEvent typeEvent, String description,String location, Date datedebut, Date datefin, Integer maxpart
    );

    @Query("select e from Event e left join e.participants p group by e order by count(p) desc")
    List<Event> eventsorted();


}
