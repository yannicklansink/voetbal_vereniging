package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_team")
    @SequenceGenerator(
            name = "seq_team",
            initialValue = 1
    )
    private int id;

    @Column(nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private List<Trainer> trainers;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
