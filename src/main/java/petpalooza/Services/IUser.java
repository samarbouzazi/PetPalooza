package petpalooza.Services;

import petpalooza.Entities.User;

import java.util.List;

public interface IUser {
    public List<User> retrieveallUsers();
    public User updateUser(User user);
    public User addeUser(User user);
    public void deletUser(Long id);
}
