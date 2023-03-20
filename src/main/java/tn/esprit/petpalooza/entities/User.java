package tn.esprit.petpalooza.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numUser;


    @OneToMany(mappedBy = "user")
    private List<Event> events;
}
