package nl.belastingdienst.voetbal_vereniging.model.junction_table;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Training;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class PlayerHasGame {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    private Game game;



}
