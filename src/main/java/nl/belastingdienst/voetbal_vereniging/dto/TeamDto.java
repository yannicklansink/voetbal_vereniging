package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
public class TeamDto implements DtoEntity{

    private int id;

}
