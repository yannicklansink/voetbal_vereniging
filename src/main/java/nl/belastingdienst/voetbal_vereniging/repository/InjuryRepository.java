package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Injury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InjuryRepository extends JpaRepository<Injury, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Injury t where t.id = ?1")
    void delete(int entityId);

}
