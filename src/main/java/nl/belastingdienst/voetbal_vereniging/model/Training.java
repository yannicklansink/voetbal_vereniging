package nl.belastingdienst.voetbal_vereniging.model;

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
