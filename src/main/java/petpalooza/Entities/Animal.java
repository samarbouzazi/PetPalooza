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

@Table(name = "Animal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Animal implements Serializable  {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAnimal;
    String nameAnimal;
    Date birthDate;
    String race;
    String description;
    String gender;
    String image;

    @ManyToOne
    User userAnimal;

    @OneToMany(mappedBy = "animal")
    @JsonIgnore
    List<RatingAnimal> ratings;

    @ManyToMany(mappedBy = "interestedAnimals")
    @JsonIgnore
    List<User> interestedUsers;

}