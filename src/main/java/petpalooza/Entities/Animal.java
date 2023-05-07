package petpalooza.Entities;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

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
    private String nameAnimal;


    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String race;
    private String description;
    private String gender;
    private String image;

    private int likes;
    public void setLikes(int likes) {this.likes = likes;
    }
    private int dislikes;
    public void setDislikes (int dislikes) {
        this.dislikes = dislikes;
    }

    @ManyToOne
    User userAnimal;

    @OneToMany(mappedBy = "animal")

    // ,cascade = CascadeType.ALL)
   // @JsonIgnore
    //orphanRemoval = true

            // ,cascade = CascadeType.ALL)
    @JsonIgnore
            //orphanRemoval = true

    List<RatingAnimal> ratings;

    @ManyToMany(mappedBy = "interestedAnimals", cascade = CascadeType.ALL)
    List<User> interestedUsers;
}