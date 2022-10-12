package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Integer> {

    @Transactional
    @Modifying
    @Query("select r.id from Referee r where r.refereeName = ?1")
    List<Integer> findRefereeByRefereeName(String refereeName);

}
