package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto implements DtoEntity{

    private Date date;
}
