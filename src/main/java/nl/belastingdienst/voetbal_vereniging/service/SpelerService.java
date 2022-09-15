package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Speler;
import nl.belastingdienst.voetbal_vereniging.repository.SpelerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new RecordNotFoundException("There are no tickets in the database");
        }
    }

    public Optional<Speler> getSpelerById(int id) {

    }

    public void addNewSpeler(Speler speler) {

    }

    public void updateSpelerById(Speler speler) {
        Optional<Speler> newTicket = repository.findById(speler.getId());
        if (newTicket.isPresent()) {
            repository.save(speler);
        } else {
            throw new RecordNotFoundException("This id is not used");
        }
    }

    public void deleteSpelerById(int id) {

    }
}
