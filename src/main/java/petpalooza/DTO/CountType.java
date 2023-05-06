package petpalooza.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import petpalooza.Entities.TypeEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountType {
    private Long count;
    private TypeEvent type;
    /*private  String offretype ;*/
}
