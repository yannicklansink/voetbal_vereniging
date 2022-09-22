package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Trainer {

    @Id
    @GeneratedValue
    private int id;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Referee name is mandatory")
    private String refereeName;

    private String street;

    private int houseNumber;

    private String postalCode;

    @OneToMany(mappedBy = "trainer")
    private List<Training> trainings;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "trainer")
    private List<Game> games;

    public Trainer(String refereeName, String street, int houseNumber, String postalCode) {
        this.refereeName = refereeName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }
}
