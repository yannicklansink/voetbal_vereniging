package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PlayerHasTraining {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    private boolean isAanwezig; // boolean standard value = false

    @Column(length=350, nullable=true, unique=false)
    private String reasonForAbsent;

    private Date date;


}
