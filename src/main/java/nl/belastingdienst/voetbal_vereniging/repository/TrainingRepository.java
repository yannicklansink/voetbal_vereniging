package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

}
