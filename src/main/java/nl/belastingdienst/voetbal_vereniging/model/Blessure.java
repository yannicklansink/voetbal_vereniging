package nl.belastingdienst.voetbal_vereniging.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Blessure {

    @Id
    @GeneratedValue
    private int id;

}
