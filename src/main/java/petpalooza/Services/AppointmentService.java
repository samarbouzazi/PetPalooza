package petpalooza.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Appointment;
import petpalooza.Entities.User;
import petpalooza.Repositories.AppointmentRepository;
import petpalooza.Repositories.UserRepository;
import petpalooza.Services.userServices.IUser;
import petpalooza.Services.userServices.UserService;


import java.util.List;
@Service
public class AppointmentService implements IAppointment{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Appointment> getAllAppointment() {
        return  appointmentRepository.findAll();
    }


    //THIS METHOD CAN BE USED FOR INSERTING AS WELL AS UPDATING
    //IF YOU WANT TO ADD, PASS THE APPOINTMENT OBJECT WITHOUT AN ID
    //IF YOU WANT TO UPDATE, PASS THE APPOINTMENT OBJECT WITH AN EXISTING ID
    @Override
    public Appointment addNewAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    //THIS WON'T BE USED
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

        user1= userService.findUserByID(idUser1);
        user2= userService.findUserByID(idUser2);

        Appointment appointment=new Appointment();
        appointment.setNormalUser(user1);

        appointment.setNormalUser(user2);
        return  appointmentRepository.save(appointment);


    }


    //ORDER BY PRICE METHODS
    @Override
    public List<Appointment> OrderByPriceDescending() {
        return appointmentRepository.PriceDesc();
    }

    @Override
    public List<Appointment> OrderByPriceAscending() {
        return appointmentRepository.PriceAsc();
    }

    //ORDER BY DATE METHODS
    @Override
    public List<Appointment> orderByDateRdvDescending() {
        return this.appointmentRepository.ordredByDateRdvDescending();
    }


    @Override
    public List<Appointment> orderByDateRdvAscending() {
        return this.appointmentRepository.ordredByDateRdvAscending();
    }


    @Override
    public List<Appointment> getOnlyRdvToday() {
        return this.appointmentRepository.getOnlyRdvToday();
    }


    //FILTER BY USER METHODS
    @Override
    public List<Appointment> findByUser(long idUser) {
        return this.appointmentRepository.getByUser(idUser);
    }

    @Override
    public List<Appointment> findByUserOrderedDateDescending(long idUser) {
        return this.appointmentRepository.getByUserOrderedByDateDescending(idUser);
    }


    @Override
    public List<Appointment> findByUserOrderedDateAscending(long idUser) {
        return this.appointmentRepository.getByUserOrderedByDateAscending(idUser);
    }


    @Override
    public List<Appointment> findByUserToday(long idUser) {
        return this.appointmentRepository.getByUserToday(idUser);
    }


    //FILTER BY VET METHODS
    @Override
    public List<Appointment> findByVet(long idVet) {
        return this.appointmentRepository.getByVet(idVet);
    }


    @Override
    public List<Appointment> findByVetOrderedDescending(long idVet) {
        return this.appointmentRepository.getByVetOrderedByDateDescending(idVet);
    }


    @Override
    public List<Appointment> findByVetOrderedAscending(long idVet) {
        return this.appointmentRepository.getByVetOrderedByDateAscending(idVet);
    }


    @Override
    public List<Appointment> findByVetToday(long idVet) {
        return this.appointmentRepository.getByVetToday(idVet);
    }
}