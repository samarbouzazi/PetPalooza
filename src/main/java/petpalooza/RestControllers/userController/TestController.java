package petpalooza.RestControllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/test")

@RestController
@RequestMapping("api/test")
@CrossOrigin
public class TestController {


  @Autowired
  UserRepository userRepository;
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
//  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
//  @PreAuthorize("hasRole('MANAGER')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  //@PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

  @GetMapping("/vet")
//  @PreAuthorize("hasRole('VETERNIARY')")
  public String veterinaryAccess() {
    return "veterinary PAGE.";
  }

  @GetMapping("/assoc")
//@PreAuthorize("hasRole('ADMIN')")
  public String AssociationAccess() {
    return "ASSOSITION PAGE.";
  }

  @GetMapping("/profile")
//@PreAuthorize("ADMIN")
  public String getTheUserDetails(Authentication authentication){
    User user  =  userRepository.findByUsername(authentication.getName());
    Long idUser =user.getIdUser();
    String email= user.getEmail();

    return "the email is " + email + "  and the id is "+ idUser + " for user "+ user.getUsername();
  }


}
