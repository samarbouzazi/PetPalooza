package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.util.Set;


@Entity
@Table(name = "Event")
@Data
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEvent;

    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    private String description;

    private Integer MaxParticipants;

    @Temporal (TemporalType.DATE)
    private Date dateDebut;

    @Temporal (TemporalType.DATE)
    private Date dateFin;

//    @ManyToOne(optional = false)
//    @JsonIgnore
//    User user;

    @ManyToOne(optional = false)
    User owner;


    @ManyToMany()
    @JsonIgnore
    private Set<User> interestedUsers ;

    @ManyToMany()
    @JsonIgnore
    private Set<User> participants ;

}
