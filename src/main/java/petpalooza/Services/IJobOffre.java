package petpalooza.Services;

import petpalooza.Entities.JobOffer;
import petpalooza.Entities.User;

import java.util.List;

public interface IJobOffre {
    public List<JobOffer> findAllByPrice() ;

    public JobOffer addJobOffer(JobOffer jobOffer);
    public JobOffer updateJobOffer(JobOffer jobOffer);
    public void deleteJobOffer(long id);
    public List<JobOffer> getAllJobOffers();
    public JobOffer getById (long id);

    List<JobOffer> getJobOffre();

    JobOffer recommendJobOffer();

    //*************************** recommendjob ****************************************//
    public JobOffer recommendJobOffer(User user);
    public  String JobOffreStat();
    List<JobOffer> search(float price);

    public List<JobOffer> filterByOffretype(String offretype);
}
