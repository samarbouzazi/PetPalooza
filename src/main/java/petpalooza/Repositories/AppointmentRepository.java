package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petpalooza.Entities.Animal;
import petpalooza.Entities.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


//    SELECT * FROM appointment ORDER BY appointment.price ASC

    @Query("SELECT app FROM Appointment app ORDER BY app.price ASC")
    public List<Appointment> PriceAsc();
}
