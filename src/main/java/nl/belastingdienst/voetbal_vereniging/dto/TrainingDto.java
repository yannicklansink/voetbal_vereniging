package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto implements DtoEntity{

    private Date date;

    private Trainer trainer;

}
