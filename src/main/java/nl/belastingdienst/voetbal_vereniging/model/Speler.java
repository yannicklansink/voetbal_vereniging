package nl.belastingdienst.voetbal_vereniging.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Speler {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Speler(int id, String name) {
        this.name = name;
    }

    public Speler() {
    }
}
