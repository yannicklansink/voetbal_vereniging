package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import nl.belastingdienst.voetbal_vereniging.model.Gender;

import java.util.Date;

@Data
@NoArgsConstructor
public class PlayerDto {

    private String playerName;

    @NonNull
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
