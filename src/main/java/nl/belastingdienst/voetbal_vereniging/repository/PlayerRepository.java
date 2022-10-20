package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from player_data p where p.top_speed < ?1", nativeQuery = true)
    void deletePlayerBySpeed(double topSpeed);


    @Transactional
    @Modifying
    @Query("delete from PlayerData t where t.player = ?1")
    void deleteByPlayer(Player player);


}






