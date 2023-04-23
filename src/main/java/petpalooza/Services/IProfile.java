package petpalooza.Services;

import petpalooza.Entities.Profile;

public interface IProfile {
	 public Profile getProfileByFullName(String name);
	 public Profile updateProfile(Profile profile);
	 public boolean isDiplomaAuthentic(String diplome,String fullname);

}
