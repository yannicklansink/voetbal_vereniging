package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            initialValue = 7
    )
    private int id;

    @Column(nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "team")
    private List<Game> games;

    @OneToMany(mappedBy = "team")
    private List<Training> trainings;

    public Team(String teamName) {
        this.teamName = teamName;
    }

//    @Override
//    public String toString() {
//        return teamName;
//    }
}
