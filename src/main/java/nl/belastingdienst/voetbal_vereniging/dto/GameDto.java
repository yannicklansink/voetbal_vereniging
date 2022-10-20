package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto implements  DtoEntity {

    private LocalDate date;

    private String opponent;

    // only refereeName
    @JsonIgnoreProperties(value = {"games", "street", "houseNumber", "postalCode"})
    private RefereeDto referee;

    // only teamName
    @JsonIgnoreProperties(value = "players")
    private TeamPlayersDto team;

}
