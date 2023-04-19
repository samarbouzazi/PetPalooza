package petpalooza.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import petpalooza.Entities.Animal;
import petpalooza.Entities.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    
	//ORDERED BY PRICE
	@Query("SELECT app FROM Appointment app ORDER BY app.price ASC")
    public List<Appointment> PriceAsc();
    
    @Query("SELECT app FROM Appointment app ORDER BY app.price Desc")
    public List<Appointment> PriceDesc();
    
    //GET BY USER
    @Query("SELECT a FROM Appointment a WHERE a.userViternaire.idUser = ?1")
    public List<Appointment> getByVet(long idVet);
    
    @Query("SELECT a FROM Appointment a WHERE a.userViternaire.idUser = ?1 ORDER BY a.dateRDV DESC")
    public List<Appointment> getByVetOrderedByDateDescending(long idVet);
    
    @Query("SELECT a FROM Appointment a WHERE a.userViternaire.idUser = ?1 ORDER BY a.dateRDV ASC")
    public List<Appointment> getByVetOrderedByDateAscending(long idVet);
    
    @Query("SELECT a FROM Appointment a WHERE a.userViternaire.idUser = ?1 AND a.dateRDV = CURRENT_DATE")
    public List<Appointment> getByVetToday(long idVet);
    
    //GET BY VET
    @Query("SELECT a FROM Appointment a WHERE a.normalUser.idUser = ?1")
    public List<Appointment> getByUser(long idUser);
    
    @Query("SELECT a FROM Appointment a WHERE a.normalUser.idUser = ?1 ORDER BY a.dateRDV DESC")
    public List<Appointment> getByUserOrderedByDateDescending(long idUser);
    
    @Query("SELECT a FROM Appointment a WHERE a.normalUser.idUser = ?1 ORDER BY a.dateRDV ASC")
    public List<Appointment> getByUserOrderedByDateAscending(long idUser);
    
    @Query("SELECT a FROM Appointment a WHERE a.normalUser.idUser = ?1 AND a.dateRDV = CURRENT_DATE")
    public List<Appointment> getByUserToday(long idUser);
    
    
    //ORDER BY DATE
    @Query("SELECT a FROM Appointment a ORDER BY a.dateRDV DESC")
    public List<Appointment> ordredByDateRdvDescending();
    
    @Query("SELECT a FROM Appointment a ORDER BY a.dateRDV ASC")
    public List<Appointment> ordredByDateRdvAscending();
    
    @Query("SELECT a FROM Appointment a WHERE a.dateRDV = CURRENT_DATE")
    public List<Appointment> getOnlyRdvToday();
    
}
