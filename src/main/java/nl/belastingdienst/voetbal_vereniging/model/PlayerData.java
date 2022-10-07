package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Foot;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Position;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Star;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class PlayerData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_playerdata")
    @SequenceGenerator(
            name = "seq_playerdata",
            initialValue = 50
    )    private int id;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(nullable = true)
    private int height;

    @Column(nullable = true)
    private double weight;

    @Column(nullable = true)
    private double topSpeed;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Foot preferedFoot;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(nullable = true)
    private int workRate;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Star weakFoot;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Star skillMoves;

    @Column(nullable = true)
    private int dribbling;

    @Column(nullable = true)
    private int shooting;

    @Column(nullable = true)
    private int passing;

}
