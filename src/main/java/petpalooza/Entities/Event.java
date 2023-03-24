package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import petpalooza.Entities.User;

@Entity
@Table(name = "Event")
@Data
public class Event  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEvent;

    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    private String description;

    private Integer nbParticipants;

    @Temporal (TemporalType.DATE)
    private Date dateDebut;

    @Temporal (TemporalType.DATE)
    private Date dateFin;

  @ManyToOne
    @JsonIgnore
   User user;
}
