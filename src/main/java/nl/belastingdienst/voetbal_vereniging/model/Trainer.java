package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_trainer")
    @SequenceGenerator(
            name = "seq_trainer",
            initialValue = 10
    )
    private int id;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Trainer name is mandatory")
    private String trainerName;

    private String street;

    @Column(nullable = true)
    private int houseNumber;

    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Trainer(String trainerName, String street, int houseNumber, String postalCode) {
        this.trainerName = trainerName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public Trainer(String trainerName, Team team) {
        this.trainerName = trainerName;
        this.team = team;
    }

    public Trainer(String trainerName) {
        this.trainerName = trainerName;
    }
}
