package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InjuryDto implements DtoEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String explanation;

    // only id and playerName
    @JsonIgnoreProperties(value = {"street", "houseNumber", "postalCode", "age", "birthDate", "gender", "injury", "playerData", "team"})
    private Player player;
}
