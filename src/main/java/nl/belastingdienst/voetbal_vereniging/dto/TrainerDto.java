package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.model.Team;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
public class TrainerDto implements DtoEntity {

    private String trainerName;

    private String street;

    private int houseNumber;

    private String postalCode;

    private TeamDto team;


}
