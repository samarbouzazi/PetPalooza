package petpalooza.RestControllers.userController.authController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.ERole;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
import petpalooza.Repositories.RoleRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.userServices.CustomHttpStatus;
import petpalooza.security.jwt.JwtUtils;
import petpalooza.security.payload.request.LoginRequest;
import petpalooza.security.payload.request.SignupRequest;

import petpalooza.security.payload.response.JwtResponse;
import petpalooza.security.payload.response.MessageResponse;
import petpalooza.security.predifinedClasses.UserDetailsImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin()
@RestController
@RequestMapping("/public")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    System.out.println("new Login \n ----------------\n");
    System.out.println("username  \n" + loginRequest.getUsername());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getIdUser(),
                         userDetails.getUsername(),
                         userDetails.getEmail(),
                         roles));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }

      return ResponseEntity.status(CustomHttpStatus.USERNAME_ALREADY_TAKEN.value())
              .body(new MessageResponse("Error: Username is already taken!" + CustomHttpStatus.USERNAME_ALREADY_TAKEN.value()));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.status(CustomHttpStatus.EMAIL_ALREADY_IN_USE.value())
              .body(new MessageResponse("Error: Email is already in use!" + CustomHttpStatus.EMAIL_ALREADY_IN_USE.value()));


    }
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByRoleName(ERole.ROLE_MANGER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    System.out.println("ok user register successfully \n " + user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

//  @PostMapping("/logout")
//  public ResponseEntity<?> logout(HttpServletRequest request) {
//    String authHeader = request.getHeader("Authorization");
//    if (authHeader != null && authHeader.startsWith("Bearer ")) {
//      String tokenValue = authHeader.substring(7);
//      OAuth2AccessToken token = tokenStore.readAccessToken(tokenValue);
//      tokenStore.removeAccessToken(token);
//    }
//    return ResponseEntity.ok().build();
//  }


}
