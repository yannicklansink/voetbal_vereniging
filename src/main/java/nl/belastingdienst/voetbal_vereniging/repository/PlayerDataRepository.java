package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.model.enumeration.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerDataRepository extends JpaRepository<PlayerData, Integer> {

    @Transactional
    @Modifying
    @Query("delete from PlayerData t where t.id = ?1")
    void delete(int entityId);

    @Transactional
    @Modifying
    @Query("delete from PlayerData t where t.player = ?1")
    void deleteByPlayer(Player player);


    @Transactional
    @Modifying
    @Query(value = "select * from player_data p where p.position= ?1", nativeQuery = true)
    List<PlayerData> findPlayersScoutingList(String playerPosition);
}
