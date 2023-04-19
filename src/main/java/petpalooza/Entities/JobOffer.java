package petpalooza.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    float price;
    @Temporal (TemporalType.DATE)
    Date beginnigDate;
    @Temporal (TemporalType.DATE)
    Date endDate;
    String title;
    String description;
    String offretype;



  @ManyToOne
  @JsonIgnore
  User userOffer;
}
