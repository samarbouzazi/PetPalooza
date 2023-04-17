package petpalooza.RestControllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IUser;
import petpalooza.security.payload.response.MessageResponse;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("public/auth")
public class RegisterController {
    @Autowired
    IUser  iUser;


    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {

//        if(bindingResult.hasErrors()){ , BindingResult bindingResult
        if (iUser.usernameAlreadyExist(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username"+ user.getUsername() + "  is already taken!, please try another one "));
        }

        if (iUser.emailExist(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email address "+ user.getEmail() + "  is already used !, please check your input "));
        }

        iUser.addNewUser(user);
        return ResponseEntity.ok(new MessageResponse   ("User registered successfully!!!"));

    }




}

