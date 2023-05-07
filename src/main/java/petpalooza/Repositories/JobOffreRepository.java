package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import petpalooza.DTO.CountType;
import petpalooza.Entities.Event;
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
    @Query("SELECT a from JobOffer a ORDER BY a.price")
    public  List<JobOffer> findAllByOrderByPrice();

    @Transactional
    @Modifying
    @Query("UPDATE JobOffer e SET e.nbintereteds = e.nbintereteds + 1 WHERE e.idJob = :offreId")
    void incrementerNbInteresses( Long offreId);

    /*@Query(value ="select new petpalooza.DTO.CountType(COUNT(*),offretype) from JobOffer GROUP BY offretype")
    public List<CountType> statistque();*/
    @Query("SELECT j FROM JobOffer j WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:offretype IS NULL OR j.offretype = :offretype) " +
            "AND (:price IS NULL OR j.price = :price) " +
            "AND (:localisation IS NULL OR j.localisation LIKE %:localisation%) " +
            "AND (:beginnigDate IS NULL OR j.beginnigDate >= :beginnigDate) " +
            "AND (:endDate IS NULL OR j.endDate <= :endDate)")
    List<JobOffer> searchJobOffer(@Param("title") String title, @Param("offretype") String offretype, @Param("price") Integer price, @Param("localisation") String localisation, @Param("beginnigDate") String beginnigDate, @Param("endDate") String endDate);


}
