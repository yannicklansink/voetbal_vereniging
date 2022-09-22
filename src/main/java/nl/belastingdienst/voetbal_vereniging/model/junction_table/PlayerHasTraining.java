package nl.belastingdienst.voetbal_vereniging.model.junction_table;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Training;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PlayerHasTraining {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "training_id", insertable = false, updatable = false)
    private Training training;

    private boolean isAanwezig; // boolean standard value = false

    @Column(length=350, nullable=true, unique=false)
    private String reasonForAbsent;

    private Date date;


}
