package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import petpalooza.Entities.JobOffer;

import java.util.List;

public interface JobOffreRepository extends JpaRepository<JobOffer, Long> {
    @Query("Select a from JobOffer a where a.price= ?1")
    public List<JobOffer> searchByPrice(float price);
    @Query("Select a from JobOffer a where a.offretype= ?1")
    public List<JobOffer> filterByOffreType (String offretype);

    ////tri
    public  List<JobOffer> findAllByOrderByPrice();


}
