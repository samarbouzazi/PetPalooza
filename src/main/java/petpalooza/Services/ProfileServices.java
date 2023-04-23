package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import petpalooza.Entities.Profile;

import petpalooza.Repositories.ProfileRepository;
@Service
@Data
@AllArgsConstructor
public class ProfileServices implements IProfile {

	
	  @Autowired
	    ProfileRepository profileRepository;
	
	  
	  
	    public Profile updateProfile(Profile profile) {
	        return profileRepository.save(profile);
	    }

	    
	    public Profile getProfileByFullName(String fullname) {
	        Profile profile = profileRepository.findByFullName(fullname);
	        return profile ;
	    }

	  
	    public boolean isDiplomaAuthentic(String diplome,String fullname) {
	    
	        // Check if diploma is issued by the National School of Veterinary Medicine of Sidi Thabet
	        boolean issuedBySchool = diplome.contains("National School of Veterinary Medicine of Sidi Thabet");
	  
			boolean licensedByOrder = checkWithOrder(fullname);

	        return issuedBySchool;
	    }
	    private boolean checkWithOrder(String fullName) {
	        // Contact Tunisian Order of Veterinarians to check if diploma holder is licensed
	    	return true;
	    }


		


	
	    
	    
	    
}
