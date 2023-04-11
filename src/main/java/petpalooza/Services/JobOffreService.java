package petpalooza.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import petpalooza.Entities.JobOffer;
import petpalooza.Repositories.JobOffreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class JobOffreService implements IJobOffre {

    JobOffreRepository  jobOffreRepository;
    @Override
    public JobOffer addJobOffer (JobOffer jobOffer) {
        return jobOffreRepository.save(jobOffer);

    }

    @Override
    public JobOffer updateJobOffer(JobOffer jobOffer) {
        return jobOffreRepository.save(jobOffer);
    }

    @Override
    public void deleteJobOffer(long id) {
        jobOffreRepository.deleteById(id);
    }

    @Override
    public List<JobOffer> getAllJobOffers() {

        return jobOffreRepository.findAll();

    }

    @Override
    public JobOffer getById(long id) {

        return jobOffreRepository.findById(id).get();

    }
}

