package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Appointment;
import petpalooza.Entities.User;
import petpalooza.Repositories.AppointmentRepository;

import java.util.List;
@Service
public class AppointmentService implements IAppointment{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    IUser iUser;
    @Override
    public List<Appointment> getAllAppointment() {
        return  appointmentRepository.findAll();
    }

    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        return  appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public void deleteAppointment(Long idAppointment) {

        appointmentRepository.deleteById(idAppointment);

    }

    @Override
    public Appointment findAppointmentByID(Long idAppointment) {
        return appointmentRepository.findById(idAppointment).orElse(null);
    }

    @Override
    public Appointment add2(Long idUser1, Long idUser2) {

        User user2=new User();
        User user1=new User();

        user1= iUser.findUserByID(idUser1);
        user2= iUser.findUserByID(idUser2);

        Appointment appointment=new Appointment();
        appointment.setNormalUser(user1);

        appointment.setNormalUser(user2);
        return  appointmentRepository.save(appointment);


    }
}
