package petpalooza.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import petpalooza.Entities.JobOffer;
import petpalooza.Entities.User;
import petpalooza.Repositories.JobOffreRepository;

import java.util.*;

@Service
@AllArgsConstructor

public class JobOffreService implements IJobOffre {


    JobOffreRepository  jobOffreRepository;
    EmailSender emailSender;
    UserService userService;
    @Override
    public List<JobOffer> findAllByPrice() {
        return jobOffreRepository.findAllByOrderByPrice();
    }

    //*************************** mailing ****************************************//
    @Override
    public JobOffer addJobOffer (JobOffer jobOffer) {
        JobOffer job=jobOffreRepository.save(jobOffer);
        List<User> userList=userService.retrieveallUsers();
        for(User user :userList){
            if ( user!= job.getUserOffer())
            {
                emailSender.send(user.getEmail(), "Peu importe que vous soyez un utilisateur on a un offre de travail !\n" +
                        "nom:"+job.getTitle()+"\n Description:" +job.getDescription());


            }
        }
        return job;

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
    @Override
    public List<JobOffer> getJobOffre() {
        return jobOffreRepository.findAllByOrderByPrice();
    }

    @Override
    public JobOffer recommendJobOffer() {
        return null;
    }

    //*************************** recommendjob ****************************************//
    @Override
    public JobOffer recommendJobOffer(User user) {
        List<String> interests = user.getInterests();
        List<JobOffer> jobOffers = jobOffreRepository.findAll();
        Map<String, Long> interestCounts;
        interestCounts = new HashMap<>();
        for (String interest : interests) {
            Long count = (Long) ((HashMap<?, ?>) interestCounts).get(interest);
            if (count == null) {
                count = 0L;
            }
            interestCounts.put(interest, count + 1);
        }
        String favoriteInterest = null;
        Long maxCount = 0L;
        for (Map.Entry<String, Long> entry : interestCounts.entrySet()) {
            String interest = entry.getKey();
            Long count = entry.getValue();
            if (count > maxCount) {
                favoriteInterest = interest;
                maxCount = count;
            }
        }
        List<JobOffer> matchingJobOffers = new ArrayList<>();
        for (JobOffer jobOffer : jobOffers) {
            if (jobOffer.getDescription().contains(favoriteInterest) || jobOffer.getTitle().contains(favoriteInterest)) {
                matchingJobOffers.add(jobOffer);
            }
        }
        int index = new Random().nextInt(matchingJobOffers.size());
        return matchingJobOffers.get(index);
    }
    ///****************STAT*************************************//
    @Override
    public String JobOffreStat() {
        int countoffretype=0;
        int countdemandetype=0;

        List<JobOffer> joboffreliste=jobOffreRepository.findAll();
        for(JobOffer jobOffer : joboffreliste )
        {
            if (jobOffer.getOffretype()!=null)
            {
                System.out.println(jobOffer.getOffretype());

                if (jobOffer.getOffretype().toUpperCase(Locale.ROOT).equals("OFFRE")) {
                    countoffretype++;
                } else
                    countoffretype++;
            }

        }
        int sumoffre=countoffretype+countdemandetype;

       // return sumoffre+" \t total \t"+joboffreliste.size()+"offre type has"+countoffretype*sumoffre/100+"  %  \t and demande type has "+countdemandetype*sumoffre/100+"%";
        return "as joboffre we have in total : "+ countoffretype + "\t and as job request we do have  \t"+ countdemandetype;
    }

    @Override
    public List<JobOffer> search(float price) {
        return jobOffreRepository.searchByPrice(price) ;
    }


}

