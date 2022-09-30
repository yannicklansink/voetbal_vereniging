package nl.belastingdienst.voetbal_vereniging.model.junction_table;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.model.Training;

import javax.persistence.*;

//@Data
//@NoArgsConstructor
//@Entity
//public class PlayerHasGame {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_playergame")
//    @SequenceGenerator(
//            name = "seq_playergame",
//            initialValue = 1
//    )
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "player_id", insertable = false, updatable = false)
//    private Player player;
//
//    @ManyToOne
//    @JoinColumn(name = "game_id", insertable = false, updatable = false)
//    private Game game;
//
//    @ManyToOne
//    @JoinColumn(name = "trainer_id", insertable = false, updatable = false)
//    private Trainer trainer;
//
//
//
//}
