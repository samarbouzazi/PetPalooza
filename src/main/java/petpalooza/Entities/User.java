package petpalooza.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numUser;

    private String nom;

    private String prenom;

    @OneToMany(mappedBy = "user")
    List<Event> events;
}
