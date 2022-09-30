package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Player;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
public class InjuryDto implements DtoEntity {

    private Date startDate;

    private Date endDate;

    private String explanation;

    private Player player;
}