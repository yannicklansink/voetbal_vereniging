package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Injury {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_injury")
    @SequenceGenerator(
            name = "seq_injury",
            initialValue = 1
    )
    private int id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private String explanation;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Injury(Date startDate, Date endDate, String explanation, Player player) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.explanation = explanation;
        this.player = player;
    }
}
