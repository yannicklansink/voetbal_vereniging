package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
//    User findByUsername(String username);
}
