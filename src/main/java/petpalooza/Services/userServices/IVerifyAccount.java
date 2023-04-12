package petpalooza.Services.userServices;

import petpalooza.Entities.User;

public interface IVerifyAccount {

//    public void updatePassword(User user, String newPassword);

    public void UpdateActiveStatus(String email);

    public User getUserByEmail(String email);
}
