package petpalooza.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "JobOffer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class JobOffer implements Serializable  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long idJob;
    Integer price;
    @Temporal (TemporalType.DATE)
    Date beginnigDate;
    @Temporal (TemporalType.DATE)
    Date endDate;
    String title;
    String description;
    String offretype;
    int nbintereteds;
    String localisation;
    private String image ;
    @ManyToMany()
    @JsonIgnore
    private Set<User> interestedUserss ;






  @ManyToOne
  @JsonIgnore
  User userOffer;
}
