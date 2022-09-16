package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Speler;
import nl.belastingdienst.voetbal_vereniging.repository.SpelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class SpelerService {

    private SpelerRepository repository;

    @Autowired
    public SpelerService(SpelerRepository repository) {
        this.repository = repository;
    }


    public List<Speler> getAllSpelers() {
        if (repository.count() != 0) {
            return repository.findAll();
        } else {
            throw new RecordNotFoundException("There are no players in the database");
        }
    }

    public Optional<Speler> getSpelerById(int id) {
        Optional<Speler> newSpeler = null;
        if (checkIfIdExists(id)) {
            newSpeler = repository.findById(id);
        }
        return newSpeler;
    }

    public void addNewSpeler(Speler speler) {
        repository.save(speler);
    }

    public void updateSpelerById(Speler speler, int id) {
        if (checkIfIdExists(id)) {
            repository.deleteById(id);
            repository.save(speler);
        }

    }

    public void deleteSpelerById(int id) {
        if (checkIfIdExists(id)) {
            repository.deleteById(id);
        }

    }

    public boolean checkIfIdExists(int id) {
        Optional<Speler> newSpeler = repository.findById(id);
        if (newSpeler.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }


}
