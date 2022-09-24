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
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private List<Trainer> trainers;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
