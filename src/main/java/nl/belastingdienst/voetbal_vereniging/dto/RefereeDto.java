package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class RefereeDto implements DtoEntity {

    private String refereeName;

    private String street;

    private int houseNumber;

    private String postalCode;

    @JsonIgnoreProperties(value = "referee")
    private List<GameDto> games;
}
