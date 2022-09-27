package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.junction_table.PlayerHasGame;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Opponent is mandatory")
    private String opponent;

//    @ManyToOne
//    @JoinColumn(name = "trainer_id")
//    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @OneToMany(mappedBy = "game")
    private List<PlayerHasGame> games;

    public Game(Date date, String opponent, Referee referee, List<PlayerHasGame> games) {
        this.date = date;
        this.opponent = opponent;
        this.referee = referee;
        this.games = games;
    }


}
