package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Speler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpelerRepository extends JpaRepository<Speler, Integer> {

}
