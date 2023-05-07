package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import petpalooza.DTO.CountType;
import petpalooza.Entities.Event;
import petpalooza.Entities.TypeEvent;
import petpalooza.Entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

//    List<Event> findAllByOrderByParticipantsDesc();

    //List<Event> findAllByOrderByParticipantsDesc();
//    List<Event> findAllByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrMaxParticipantsOrType(
//            String title, String location, String description, Date datedebut, Date datefin, Integer maxpart, TypeEvent typeEvent
//    );

    @Query("select e from Event e where e.titre=?1 or e.type=?1 or e.description=?1 or e.location=?1 or e.dateDebut =?1 or e.dateFin=?1 or e.maxParticipants=?1 ")
    List<Event> searchEventsByTitreOrLocationOrDescriptionOrDateDebutOrDateFinOrMaxParticipantsOrType(
            String title, TypeEvent typeEvent, String description,String location, Date datedebut, Date datefin, Integer maxpart
    );

    @Query("select e from Event e left join e.participants p group by e order by count(p) desc")
    List<Event> eventsorted();



    @Query(value ="select new petpalooza.DTO.CountType(COUNT(*),type) from Event GROUP BY type")
    public List<CountType> statistque();


    List<Event> findByDateDebut(Date dateDebut);



    @Query("SELECT e FROM Event e WHERE (:titre IS NULL OR e.titre LIKE %:titre%) " +
            "AND (:type IS NULL OR e.type = :type) " +
            "AND (:maxParticipants IS NULL OR e.maxParticipants = :maxParticipants) " +
            "AND (:location IS NULL OR e.location LIKE %:location%) " +
            "AND (:dateDebut IS NULL OR e.dateDebut >= :dateDebut) " +
            "AND (:dateFin IS NULL OR e.dateFin <= :dateFin)")
    List<Event> searchEvents(@Param("titre") String titre, @Param("type") String type, @Param("maxParticipants") Integer maxParticipants, @Param("location") String location, @Param("dateDebut") String dateDebut, @Param("dateFin") String dateFin);


}
