package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.util.Set;


@Entity
@Table(name = "Event")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEvent;

    private String Titre;

    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    private String description;

    private String location;

    private Integer MaxParticipants;

    @Temporal (TemporalType.DATE)
    private Date dateDebut;

    @Temporal (TemporalType.DATE)
    private Date dateFin;


    private String image;

    @ManyToOne
    @JsonIgnore
    User owner;


    @ManyToMany()
    @JsonIgnore
    private Set<User> interestedUsers ;

    @ManyToMany()
    @JsonIgnore
    private Set<User> participants ;



}
