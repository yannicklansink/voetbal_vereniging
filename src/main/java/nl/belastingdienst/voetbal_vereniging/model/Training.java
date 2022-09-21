package nl.belastingdienst.voetbal_vereniging.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Training {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "training")
    private List<Player> playerList;



}
