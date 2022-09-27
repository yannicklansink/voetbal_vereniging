package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Game;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
public class RefereeDto implements DtoEntity {

    private String refereeName;

    private String street;

    private int houseNumber;

    private String postalCode;

    private List<Game> games;
}
