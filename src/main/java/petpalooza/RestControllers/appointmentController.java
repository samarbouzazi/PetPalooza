package petpalooza.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petpalooza.Entities.Appointment;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Services.IAppointment;
import petpalooza.Services.IUser;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("appointment")
public class appointmentController {
    @Autowired
    IAppointment iAppointment;
    @Autowired
    IUser iUser;


    @GetMapping("list")
    public List<Appointment> listofAppointement(){
       return iAppointment.getAllAppointment();
    }
    @PostMapping("/add")
    public Appointment addEvent(@RequestBody Appointment appointment){
        return iAppointment.addNewAppointment(appointment);
    }

    @PutMapping("/update")
    public Appointment updateEvent(@RequestBody Appointment appointment){
        return iAppointment.updateAppointment(appointment);
    }

    @DeleteMapping("/delete/{idAppointement}")
    public void delete(@PathVariable("idAppointement") Long idAppointement){
        iAppointment.deleteAppointment(idAppointement);
    }

    @GetMapping("findById/{idAppointment}")
    public Appointment AppointmentById(@PathVariable("idAppointment") Long idAppointment){
        return iAppointment.findAppointmentByID(idAppointment);
    }

    @PostMapping("/add2/{idUser1}/{idUser2}")
    public Appointment add2(@PathVariable("idUser1") Long idUser1,@PathVariable("idUser2") Long idUser2 ){
//        User user1=new User();
//        User user2=new User();
//
//
//     user1= iUser.findUserByID(idUser1);
//     user2= iUser.findUserByID(idUser2);

     return    iAppointment.add2(idUser1,idUser2);



    }


}
