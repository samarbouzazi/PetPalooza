package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petpalooza.Entities.Event;

public interface EventRepository extends JpaRepository<Event,Long> {
}
