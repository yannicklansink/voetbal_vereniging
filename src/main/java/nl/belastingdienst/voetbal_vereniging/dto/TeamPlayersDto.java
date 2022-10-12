package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamPlayersDto implements DtoEntity {

    private String teamName;

    @JsonIgnoreProperties(value = {"player", "street", "houseNumber", "postalCode", "age", "birthDate", "gender", "injury", "playerData", "teamName"})
    private List<PlayerDto> players;

}
