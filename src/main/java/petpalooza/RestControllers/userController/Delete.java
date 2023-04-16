package petpalooza.RestControllers.userController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.User;
import petpalooza.Repositories.UserRepository;
import petpalooza.security.LoginViewModel;
import petpalooza.security.jwt.JwtAuthenticationFilter;
import petpalooza.security.jwt.JwtProperties;
import petpalooza.security.predifineInterfaces.UserPrincipal;
import sun.security.krb5.Credentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("/enter")
public class Delete {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("enter")
public ResponseEntity<?> login(@RequestBody LoginViewModel loginViewModel) {
        try {
        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginViewModel.getUsername(),
        loginViewModel.getPassword(),
        new ArrayList<>());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Create JWT Token
        User user = userRepository.findByUsername(loginViewModel.getUsername());
        UserPrincipal principal = new UserPrincipal(user);
        String token = JWT.create()
        .withSubject(principal.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        // Return token in response
        return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        }
        }


//
//public class JwtResponse {
//    private String token;
//
//    public JwtResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}
//
//    Once you've implemented the AuthController, you can call the /login endpoint from your Angular frontend using the HttpClient module. Here's an example implementation:
//
//        scss
//@Injectable({
//
