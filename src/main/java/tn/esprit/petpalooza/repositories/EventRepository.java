package tn.esprit.petpalooza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.petpalooza.entities.Event;


public interface EventRepository extends JpaRepository<Event,Long> {

}
