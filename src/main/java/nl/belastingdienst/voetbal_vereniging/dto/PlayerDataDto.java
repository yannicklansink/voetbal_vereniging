package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Foot;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Position;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Star;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class PlayerDataDto implements DtoEntity {

    @JsonIgnoreProperties(value = "injury")
    private PlayerDto player;

    private int height;

    private int weight;

    private double topSpeed;

    @Enumerated(EnumType.STRING)
    private Foot preferedFoot;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int workRate;

    @Enumerated(EnumType.STRING)
    private Star weakFoot;

    @Enumerated(EnumType.STRING)
    private Star skillMoves;

    private int dribbling;

    private int jumping;

    private int passing;
}
