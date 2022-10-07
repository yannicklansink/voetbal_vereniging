package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Gender;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PlayerDto implements DtoEntity {

    private String playerName;

    private String street;

    private int houseNumber;

    private String postalCode;

    private int age;

    private LocalDate birthDate;

    private Gender gender;

    @JsonIgnoreProperties(value = "player")
    private List<InjuryDto> injury;

    @JsonIgnoreProperties(value = "player")
    private PlayerDataDto playerData;

    public PlayerDto(String playerName, String street, int houseNumber, String postalCode, int age, LocalDate birthDate, Gender gender) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
