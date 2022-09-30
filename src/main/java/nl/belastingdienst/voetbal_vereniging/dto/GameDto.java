package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Team;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class GameDto implements  DtoEntity {

    private Date date;

    private String opponent;

    private Referee referee;

    private Team team;

    public GameDto(Date date, String opponent, Referee referee, Team team) {
        this.date = date;
        this.opponent = opponent;
        this.referee = referee;
        this.team = team;
    }
}
