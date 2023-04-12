package petpalooza.Services;

import petpalooza.Entities.JobOffer;

import java.util.List;

public interface IJobOffre {
    public JobOffer addJobOffer(JobOffer jobOffer);
    public JobOffer updateJobOffer(JobOffer jobOffer);
    public void deleteJobOffer(long id);
    public List<JobOffer> getAllJobOffers();
    public JobOffer getById (long id);

}
