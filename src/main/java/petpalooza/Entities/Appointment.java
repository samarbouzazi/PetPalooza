package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "Appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAppointment;

	Date dateRDV;

	double price;

	String metting;
	
	double lat;
	
	double lng;

	/// idVter
	@ManyToOne
	//@JsonIgnore
	User userViternaire;

	@ManyToOne
	//@JsonIgnore
	User normalUser;

}
