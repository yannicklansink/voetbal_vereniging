package nl.belastingdienst.voetbal_vereniging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.junction_table.PlayerHasTraining;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Training {

    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @OneToMany(mappedBy = "training")
    private List<PlayerHasTraining> trainings;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Training(Date date, List<PlayerHasTraining> trainings, Trainer trainer) {
        this.date = date;
        this.trainings = trainings;
        this.trainer = trainer;
    }
}
