package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
}
