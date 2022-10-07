package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InjuryRepository extends JpaRepository<Injury, Integer> {

}
