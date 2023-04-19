package petpalooza.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import petpalooza.Services.IProfile;

@RestController
@Data
@CrossOrigin
@RequestMapping("profile")
public class ProfileController {
	@Autowired
	 IProfile iprofile;
	
	  @PutMapping("/updateProfile")
	    public Profile update(@RequestBody petpalooza.Entities.Profile profile){
	        return (Profile) iprofile.updateProfile(profile);
	    }
	  @GetMapping("/getProfile")
	    public  Profile getProfileByFullName(@PathVariable String name)
	    {
		  return (Profile) iprofile.getProfileByFullName(name);
	    }
	
}
