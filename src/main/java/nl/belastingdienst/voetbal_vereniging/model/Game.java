package nl.belastingdienst.voetbal_vereniging.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;

}
