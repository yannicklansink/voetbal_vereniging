package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Team;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto implements  DtoEntity {

    private LocalDate date;

    private String opponent;

    @JsonIgnoreProperties(value = "games")
    private RefereeDto referee;

//    @JsonIncludeProperties(value = "teamName")
//    @JsonIgnoreProperties(value = {"games", "players"})
    private TeamDto team;

}
