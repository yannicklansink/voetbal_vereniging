package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.TrainingDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private TrainingRepository repository;

    @Autowired
    public TrainingService(TrainingRepository repository) {
        this.repository = repository;
    }

    public List<TrainingDto> getAllTrainings() {
        if (repository.count() != 0) {
            return new ArrayList<>();
//            return repository.findAll()
//                    .stream()
//                    .map(this::convertSpelerToDto)
//                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no players in the database");
        }
    }
}
