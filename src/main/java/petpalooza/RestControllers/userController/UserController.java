package petpalooza.RestControllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Role;
import petpalooza.Entities.User;
import petpalooza.Services.userServices.IUser;
import petpalooza.security.payload.response.MessageResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

   /// @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<User> retrieveAllUser(){
        return iUser.getAllUser();
    }

    @GetMapping("/findbyID/{id}")
    public User getUserByID(@PathVariable("id") Long id){
        return iUser.findUserByID(id);
    }

//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id")  Long id){
//        iUser.deleteUser(id);
//        return ResponseEntity.ok(new MessageResponse("User "+
//                iUser.findUserByID(id).getUsername()
//                +"  delete it  successfully!!!"));
//    }
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable("id")  Long id){
        iUser.deleteUser(id);

    }


    @PutMapping("block/{id}")
    public ResponseEntity<?>  block(@PathVariable("id") Long id)
    {
        iUser.blockUser(id);
        return ResponseEntity.ok(new MessageResponse("user blocked"));
    }


@PutMapping("signalUser/{idUser}")
    public ResponseEntity<?> ReportAUser(@PathVariable("idUser") Long idUser)
{
return     iUser.SignalerUser(idUser);
//    User user=   iUser.findUserByID(idUser);
//
//    return ResponseEntity.ok(new MessageResponse( user.getUsername() +"is  signaler " +" the total number of signal is "+ user.getNumberOfSignal()) );
}


@GetMapping("getRole/{idUser}")
    public void getRoleToAuser(@PathVariable("idUser") long idUser){
        this.iUser.getAllUser();
}










    @GetMapping("/users/{userId}")
    public List<Role> getUserRoles(@PathVariable Long userId) {
        User user = iUser.findUserByID(userId);
        return new ArrayList<>(user.getRoles());
    }
}
