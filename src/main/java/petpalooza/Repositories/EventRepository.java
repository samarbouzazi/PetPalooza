package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByOrderByParticipantsDesc();
//    List<Event> findAllByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrOwnerOrMaxParticipantsOrParticipantsOrInterestedUsersOrType(
//            String title, String location, String description, Date datedebut, Date datefin,User owner, Integer maxpart, User parts, User inter, TypeEvent typeEvent
//    );
}
