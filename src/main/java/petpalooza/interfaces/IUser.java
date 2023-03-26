package petpalooza.interfaces;

import petpalooza.Entities.User;

import java.util.List;

public interface IUser {
    List<User> getAllUser();

    User addNewUser(User user);

    User updateUser(User user);

    void deleteUser( Long idUser);

    User findUserByID(Long idUser);
}
