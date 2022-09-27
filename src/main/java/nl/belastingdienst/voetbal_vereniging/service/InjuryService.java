package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.InjuryDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Injury;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.repository.InjuryRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InjuryService {

    private InjuryRepository repository;

    @Autowired
    public InjuryService(InjuryRepository repository) {
        this.repository = repository;
    }

    public List<InjuryDto> getAllInjuries() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertInjuryToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no injuries in the database");
        }
    }

    public Optional<InjuryDto> getInjuryById(int id) {
        Optional<InjuryDto> newInjury = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Injury> injury = repository.findById(id);
            newInjury = convertInjuryToDto(injury);
        }
        return newInjury;
    }

    public Injury addNewInjury(InjuryDto injuryDto) {
        return repository.save(convertDtoToInjury(injuryDto));
    }

    public boolean updateInjuryById(InjuryDto injuryDto, int id) {
        if (checkIfIdExists(id)) {
            Injury updatedInjury = convertDtoToExistingInjury(injuryDto, repository.findById(id).get());
            repository.save(updatedInjury);
            return true;
        }
        return false;
    }

    public boolean deleteInjuryById(int id) {
        if (checkIfIdExists(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /*
    Method will return a RecordNotFoundException when the Optional object is empty
     */
    public boolean checkIfIdExists(int id) {
        Optional<Injury> newInjury = repository.findById(id);
        if (newInjury.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private InjuryDto convertInjuryToDto(Injury injury) {
        return (InjuryDto) new DtoUtils().convertToDto(injury, new InjuryDto());
    }

    private Optional<InjuryDto> convertInjuryToDto(Optional<Injury> injury) {
        InjuryDto injuryDto = (InjuryDto) new DtoUtils().convertToDto(injury.get(), new InjuryDto());
        return Optional.of(injuryDto);
    }

    private Injury convertDtoToInjury(InjuryDto injuryDto){
        return (Injury) new DtoUtils().convertToEntity(new Injury(), injuryDto);
    }

    private Injury convertDtoToExistingInjury(InjuryDto injuryDto, Injury injury) {
        Injury newInjury = convertDtoToInjury(injuryDto);
        newInjury.setId(injury.getId());
        return newInjury;
    }

}
