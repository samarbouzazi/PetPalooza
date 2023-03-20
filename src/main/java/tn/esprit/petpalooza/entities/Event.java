package tn.esprit.petpalooza.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEvent;

    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    @Temporal(TemporalType.DATE)
    private Date dateDeb;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private String description;

    private Integer nbPart;

    @ManyToOne
    User user;


}
