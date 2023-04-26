package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import petpalooza.DTO.CountType;
import petpalooza.Entities.JobOffer;

import javax.transaction.Transactional;
import java.util.List;

public interface JobOffreRepository extends JpaRepository<JobOffer, Long> {
    @Query("Select a from JobOffer a where a.price= ?1")
    public List<JobOffer> searchByPrice(float price);
    @Query("Select a from JobOffer a where a.offretype= ?1")
    public List<JobOffer> filterByOffreType (String offretype);

   /* @Query("update JobOffer set interestedOffre=true where idJob= ?1")
    public JobOffer interestOffre(Long idJob);*/
    ////tri
    public  List<JobOffer> findAllByOrderByPrice();
    @Transactional
    @Modifying
    @Query("UPDATE JobOffer e SET e.nbintereteds = e.nbintereteds + 1 WHERE e.idJob = :offreId")
    void incrementerNbInteresses( Long offreId);
    /*@Query(value ="select new petpalooza.DTO.CountType(COUNT(*),offretype ) from JobOffer GROUP BY offretype")
    public List<CountType> statistque();*/

}
