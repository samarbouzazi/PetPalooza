package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petpalooza.Entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
