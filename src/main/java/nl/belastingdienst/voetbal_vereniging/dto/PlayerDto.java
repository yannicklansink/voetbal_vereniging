package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Gender;

import java.util.Date;

@Data
@NoArgsConstructor
public class PlayerDto implements DtoEntity {

    private String playerName;

    private String street;

    private int houseNumber;

    private String postalCode;

    private int age;

    private Date birthDate;

    private Gender gender;

    public PlayerDto(String playerName, String street, int houseNumber, String postalCode, int age, Date birthDate, Gender gender) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
