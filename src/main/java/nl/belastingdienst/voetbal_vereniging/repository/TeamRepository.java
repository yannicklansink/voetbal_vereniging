package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Transactional
    @Modifying
    @Query(value = "select * from Team t where t.team_name=?1", nativeQuery = true)
    List<Team> findTeamByTeamName(String searchingTeamName);

    @Transactional
    @Modifying
    @Query("select t.id from Team t where t.teamName = ?1")
    List<Integer> findTeamIdByTeamName(String teamName);

}
