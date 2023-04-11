package petpalooza.RestControllers.userController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/test")

@RestController
@RequestMapping("api/test")
@CrossOrigin
public class TestController {
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
//  @PreAuthorize("hasRole('ASSOSITION')")
  public String AssociationAccess() {
    return "ASSOSITION PAGE.";
  }
}
