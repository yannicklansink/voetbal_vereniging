package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainerDto implements DtoEntity {

    private String trainerName;

    private String street;

    private int houseNumber;

    private String postalCode;

    private TeamDto team;


}
