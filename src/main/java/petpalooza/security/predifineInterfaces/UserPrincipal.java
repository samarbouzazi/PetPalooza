package petpalooza.security.predifineInterfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import petpalooza.Entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
  private User user;

  public UserDetailsImpl(User user){
    this.user = user;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();

//    // Extract list of permissions (name)
//    this.user.getPermissionList().forEach(p -> {
//      GrantedAuthority authority = new SimpleGrantedAuthority(p);
//      authorities.add(authority);
//    });

    // Extract list of roles (ROLE_name)
    this.user.getRoles().forEach(r -> {
      GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
      authorities.add(authority);
    });

  }
/*
 When a user tries to authenticate themselves to the application, the security framework will call the UserDetailsService interface to retrieve the user's details.
 The implementation of UserDetailsService will typically use a UserRepository or some other data source to fetch the user's information and return an instance of UserDetails.

 */