package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    String resetPasswordToken;

    String firstName;
    String lastName;
    String email;
    Gender gender;
    String occupation;
    int active;
    String phone;
    @Temporal(TemporalType.DATE)
    Date birthDate;
    String address;
    public enum Gender{
        MALE,FEMALE
    }

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} ,
            fetch = FetchType.EAGER )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();





    ///////////////////////Event Samar/////////////
    @OneToMany(mappedBy = "user")
    List<Event> events;
   //////////////////Islem/////////////////
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userAnimal")
    private Set<Animal> animals;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<RatingAnimal> ratings;

///////////////Malek//////////////


    @OneToMany(cascade = CascadeType.ALL, mappedBy="userOffer")
    private Set<JobOffer> jobOfferss;

    /////////Iskander/////////////
    @OneToMany
    List<Appointment> appointments;


}



















