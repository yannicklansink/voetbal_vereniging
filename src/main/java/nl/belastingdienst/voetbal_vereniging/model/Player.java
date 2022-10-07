package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_player")
    @SequenceGenerator(
            name = "seq_player",
            initialValue = 50
    )
    private int playerId;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Player name is mandatory")
    private String playerName;

    private String street;

    @Column(nullable = true)
    private Integer houseNumber;

    private String postalCode;

    // Temporal: solves converting the date and time values from Java object to compatible database type
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @PrimaryKeyJoinColumn
    private PlayerData playerData;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Injury> injury;

    public Player(String playerName, String street, int houseNumber, String postalCode, LocalDate birthDate, Gender gender) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Player(String playerName, String street, int houseNumber, String postalCode, LocalDate birthDate, Gender gender, Team team) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.birthDate = birthDate;
        this.gender = gender;
        this.team = team;
    }


    public Player(String playerName, Gender gender, Team team) {
        this.playerName = playerName;
        this.gender = gender;
        this.team = team;
    }
}
