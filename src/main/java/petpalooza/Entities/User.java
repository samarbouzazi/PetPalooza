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
public class User implements Serializable {

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
    int active =0;
    String phone;
    @Temporal(TemporalType.DATE)
    Date birthDate;
    String address;

    int numberOfSignal;



    public enum Gender{
        MALE,FEMALE
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    @PreRemove
    public void removeRoles() {
        this.roles.remove(roles);
        roles.remove(this);
    }

////chat ///////
    @ManyToMany
    @JoinTable(
            name = "user_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "idMessage"))
    Set<ChatMessage> chatMessageSet= new HashSet<>();




    ///////////////////////Event Samar/////////////
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    List<Event> events;

    @ManyToMany(mappedBy = "interestedUsers" ,cascade = CascadeType.ALL)
    private Set<Event> eventInterested;

    @ManyToMany(mappedBy = "participants",cascade = CascadeType.ALL)
    private Set<Event> eventsParticipating;

   //////////////////Islem/////////////////

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userAnimal")
    private Set<Animal> animals;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<RatingAnimal> ratings;

    @ManyToMany
    @JsonIgnore
    List<Animal> interestedAnimals;

///////////////Malek//////////////

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userOffer")
    private List<JobOffer> jobOfferss;

    /////////Iskander/////////////
    @OneToMany
    List<Appointment> appointments;






//    public User(long idUser, String username, String password, String firstName, String lastName, String email, Gender gender, String occupation, int active, String phone, String address, Set<Role> role) {
//        this.idUser = idUser;
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.gender = gender;
//        this.occupation = occupation;
//        this.active = active;
//        this.phone = phone;
//        this.address = address;
//        this.roles=role;
//    }
//    public User(String username, String email, String password, String phone, String address, String occupation) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.phone=phone;
//        this.address=address;
//        this.occupation=occupation;
//    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public int getNumberOfSignal() {
        return numberOfSignal;
    }

    public void setNumberOfSignal(int numberOfSignal) {
        this.numberOfSignal = numberOfSignal;
    }
}



















