package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_game")
    @SequenceGenerator(
            name = "seq_game",
            initialValue = 1
    )
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Opponent is mandatory")
    private String opponent;

//    @ManyToOne
//    @JoinColumn(name = "trainer_id")
//    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Game(LocalDate date, String opponent, Referee referee, Team team) {
        this.date = date;
        this.opponent = opponent;
        this.referee = referee;
        this.team = team;
    }


}
