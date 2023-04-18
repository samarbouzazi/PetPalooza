package petpalooza.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idRole;
    @Enumerated(EnumType.STRING)
    ERole roleName;
    //////////////////////////////


    public Role(ERole roleName) {
        this.roleName = roleName;
    }
}


