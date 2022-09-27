package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import nl.belastingdienst.voetbal_vereniging.model.junction_table.PlayerHasGame;
import nl.belastingdienst.voetbal_vereniging.model.junction_table.PlayerHasTraining;
import nl.belastingdienst.voetbal_vereniging.util.Gender;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_player")
    @SequenceGenerator(
            name = "seq_player",
            initialValue = 1
    )
    private int playerId;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Player name is mandatory")
    private String playerName;

    private String street;

    @Column(nullable = true)
    private int houseNumber;

    private String postalCode;

    // Temporal: solves converting the date and time values from Java object to compatible database type
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "player")
    private List<PlayerHasTraining> players;

    @OneToMany(mappedBy = "player")
    private List<PlayerHasGame> games;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<Injury> injury;

    public Player(String playerName, String street, int houseNumber, String postalCode, Date birthDate, Gender gender) {
        this.playerName = playerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
