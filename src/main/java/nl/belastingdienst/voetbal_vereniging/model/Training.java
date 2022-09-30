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

    @ManyToMany
    @JoinTable(
            name = "training_players",
            joinColumns = @JoinColumn(name = "player_id "),
            inverseJoinColumns = @JoinColumn(name = "training_id")
    )
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Training(LocalDate date, List<Player> players, Trainer trainer) {
        this.date = date;
        this.players = players;
        this.trainer = trainer;
    }

    public Training(LocalDate date, Trainer trainer) {
        this.date = date;
        this.trainer = trainer;
    }
}
