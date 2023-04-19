package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByOrderByParticipantsDesc();
    @Query("select e from Event e left join e.participants p group by e order by count(p) desc")
    List<Event> eventsorted();
}
