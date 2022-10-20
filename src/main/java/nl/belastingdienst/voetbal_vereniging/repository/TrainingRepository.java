package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {


    @Transactional
    @Modifying
    @Query(value = "select * from training p where p.team_id= ?1", nativeQuery = true)
    List<Training> findAllByTeamId(int teamid);
}
