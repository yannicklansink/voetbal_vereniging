package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Injury {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_injury")
    @SequenceGenerator(
            name = "seq_injury",
            initialValue = 15
    )
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String explanation;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Injury(LocalDate startDate, LocalDate endDate, String explanation, Player player) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.explanation = explanation;
        this.player = player;
    }
}
