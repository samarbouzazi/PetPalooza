package petpalooza.interfaces;

import petpalooza.Entities.Role;

import java.util.List;

public interface IRole {
     void affectRoleToUser();
     List<Role> findRoles();
}
