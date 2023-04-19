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
    
    //ORDERED BY PRICE
    List<Appointment> OrderByPriceDescending();
    List<Appointment> OrderByPriceAscending();
    
    //GET BY USER
    List<Appointment> findByUser(long idUser);
    List<Appointment> findByUserOrderedDateDescending(long idUser);
    List<Appointment> findByUserOrderedDateAscending(long idUser);
    List<Appointment> findByUserToday(long idUser);
    
    //GET BY VET
    List<Appointment> findByVet(long idVet);
    List<Appointment> findByVetOrderedDescending(long idVet);
    List<Appointment> findByVetOrderedAscending(long idVet);
    List<Appointment> findByVetToday(long idVet);
    
    //ORDER BY DATE
    List<Appointment> orderByDateRdvDescending();
    List<Appointment> orderByDateRdvAscending();
    List<Appointment> getOnlyRdvToday();
}
