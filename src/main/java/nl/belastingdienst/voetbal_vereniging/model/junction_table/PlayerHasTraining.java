package nl.belastingdienst.voetbal_vereniging.model.junction_table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Training;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class PlayerHasTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_playertraining")
    @SequenceGenerator(
            name = "seq_playertraining",
            initialValue = 1
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "training_id", insertable = false, updatable = false)
    private Training training;

    private boolean isPresent = true;

    @Column(length=350, nullable=true, unique=false)
    private String reasonForAbsent;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;


}
