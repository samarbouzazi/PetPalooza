package petpalooza.RestControllers.userController;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import petpalooza.security.LoginViewModel;
@CrossOrigin()
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        // return an empty response with the allowed headers and methods
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type");

        return ResponseEntity.ok().headers(headers).build();
    }

    // other login-related methods
}
