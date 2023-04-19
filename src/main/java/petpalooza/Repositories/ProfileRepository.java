package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import petpalooza.Entities.Profile;


public interface ProfileRepository extends JpaRepository<Profile,Long>{

	Profile findByFullName(String fullName);
    Boolean existsByemail(String email);
    
}
