package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petpalooza.Entities.JobOffer;

public interface JobOffreRepository extends JpaRepository<JobOffer, Long> {
}
