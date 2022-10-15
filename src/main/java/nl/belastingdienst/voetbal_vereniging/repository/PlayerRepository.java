package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // Spring data jpa creates an implementation of this class when we run the program.
//    List<Speler> findByLastName(String lastName);

}






