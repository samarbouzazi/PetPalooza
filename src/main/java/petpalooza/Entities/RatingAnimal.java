package petpalooza.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RatingAnimal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idRating;
    @ManyToOne
    Animal animal;
    @ManyToOne
    User user;
    boolean liked;
//ehy
}
