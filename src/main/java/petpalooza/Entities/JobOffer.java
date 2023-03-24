package petpalooza.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "JobOffer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class JobOffer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long idJob;
    float price;
    Date beginnigDate;
    Date endDate;
    String title;
    String description;


  @ManyToOne
  User userOffer;

}
