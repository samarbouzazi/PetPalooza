package petpalooza.Services;

import petpalooza.Entities.Appointment;
import petpalooza.Entities.User;

import java.util.List;

public interface IAppointment {
    List<Appointment> getAllAppointment();

    Appointment addNewAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointment( Long idAppointment);

    Appointment findAppointmentByID(Long idAppointment);



    Appointment add2( Long idUser1, Long idUser2);
}
