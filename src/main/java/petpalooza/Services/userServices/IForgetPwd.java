package petpalooza.Services.userServices;

import petpalooza.Entities.User;

public interface IForgetPwd {
    public void updatePassword(User user, String newPassword);

    public void updateResetPasswordToken(String token, String email);

    public User getByResetPasswordToken(String token);


}
