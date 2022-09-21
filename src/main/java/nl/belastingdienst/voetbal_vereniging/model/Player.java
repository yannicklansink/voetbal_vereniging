package nl.belastingdienst.voetbal_vereniging.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue
    private int playerId;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Player name is mandatory")
    private String playerName;

    private String street;

    private int houseNumber;

    private String postalCode;

    @Transient
    private int age;

    // Temporal: solves converting the date and time values from Java object to compatible database type
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "player")
    private List<Training> trainingList;

    public Player(String playerName, String street, int houseNumber, String postalCode, int age, Date birthDate, Gender gender) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
