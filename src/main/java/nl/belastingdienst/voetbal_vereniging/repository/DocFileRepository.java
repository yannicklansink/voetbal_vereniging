package nl.belastingdienst.voetbal_vereniging.repository;

import nl.belastingdienst.voetbal_vereniging.dto.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
