package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_referee")
    @SequenceGenerator(
            name = "seq_referee",
            initialValue = 1
    )
    private int id;

    @Column(length=50, nullable=false, unique=false)
    @NotBlank(message = "Referee name is mandatory")
    private String refereeName;

    private String street;

    @Column(nullable = true)
    private int houseNumber;

    private String postalCode;

    @OneToMany(mappedBy = "referee")
    private List<Game> games;


}
