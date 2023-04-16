package petpalooza.security.predifineInterfaces;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserPrincipal implements UserDetails {
  private User user;

  public UserPrincipal(User user){
    this.user = user;
  }







//@Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//  List<GrantedAuthority> authorities = new ArrayList<>();
//
//
//  // Extract list of roles (ROLE_name)
//  this.user.getRoles().forEach(r -> {
//    GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
//    authorities.add(authority);
//  });
//  return authorities;
//}


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Role> roles = user.getRoles();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(""+role.getRoleName()));
      //   System.out.println("result iss \n");

      // System.out.println(role.getName());
      //System.out.println("--------------------");
    }

    return authorities;
  }
  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
//    return this.user.getActive()==1;
    return true;
  }
}
/*
 When a user tries to authenticate themselves to the application, the security framework will call the UserDetailsService interface to retrieve the user's details.
 The implementation of UserDetailsService will typically use a UserRepository or some other data source to fetch the user's information and return an instance of UserDetails.

 */