package petpalooza.RestControllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IUser;
import petpalooza.security.payload.response.MessageResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("admin/user")
public class UserController {

    @Autowired
    IUser iUser;



//////////////////////////////

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user, HttpServletResponse response) throws IOException {
        iUser.updateUser(user);
        response.sendRedirect("http://localhost:8888/admin/list");
    }


    @GetMapping("/find/{idUser}")
    public User retriveUserByID(@PathVariable("idUser")  Long idUser){
        return  iUser.findUserByID(idUser);

    }
    @GetMapping("/list")
    public List<User> retrieveAllUser(){
        return iUser.getAllUser();
    }

    @GetMapping("/findbyID/{id}")
    public User getUserByID(@PathVariable("id") Long id){
        return iUser.findUserByID(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")  Long id){
        return ResponseEntity.ok(new MessageResponse("User "+
                iUser.findUserByID(id).getUsername()
                +"  delete it  successfully!!!"));
    }

    @PutMapping("block/{id}")
    public ResponseEntity<?>  block(@PathVariable("id") Long id)
    {
        iUser.blockUser(id);
        return ResponseEntity.ok(new MessageResponse("user blocked"));
    }


@PutMapping("signalUser/{idUser}")
    public void ReportAUser(@PathVariable("idUser") Long idUser)
{
    iUser.SignalerUser(idUser);
}

}
