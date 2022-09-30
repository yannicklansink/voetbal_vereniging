package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_training")
    @SequenceGenerator(
            name = "seq_training",
            initialValue = 1
    )
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

//    @ManyToMany
//    @JoinTable(
//            name = "training_players",
//            joinColumns = @JoinColumn(name = "player_id ", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "training_id", nullable = false)
//    )
//    private List<Player> players;
//
//    @OneToMany(mappedBy = "training")
//    private List<PlayerHasTraining> players;

//    @ManyToOne
//    @JoinColumn(name = "trainer_id")
//    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Training(LocalDate date, Team team) {
        this.date = date;
        this.team = team;
    }

    public Training(LocalDate date) {
        this.date = date;
    }
}
