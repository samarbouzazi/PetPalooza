package petpalooza.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;


@Entity

@Table(name = "Profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile implements Serializable{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long IdProfile;
	   String fullName;
	    String email;
	    String phone;
	    String address;
	    String Spécialités;
	    String Qualification_professionnelle;
	    String Diplome ;//sous forme d'un lien sftp (serveur d'image)  
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "Userid")
	    private User user;
	
}
