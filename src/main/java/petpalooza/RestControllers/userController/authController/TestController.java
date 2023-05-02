package petpalooza.RestControllers.userController.authController;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

@CrossOrigin()
@RestController
@RequestMapping("/private/user")
public class TestController {

  @Autowired
  UserRepository userRepository;
  @GetMapping("/public")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  public String moderatorAccess() {
    return "Moderator Board.";
  }
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin")
  public String adminAccess(Authorization authorization) {
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String userName = loggedInUser.getName();

    User user = userRepository.findByUsername(userName).get();
    String email = user.getEmail();
   Long id = user.getIdUser();

    return "the connected user  email is "+ email + "\n usename "+ userName   +"\n id is " + id;
  }
}
