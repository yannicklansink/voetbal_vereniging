package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.RefereeDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.repository.RefereeRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RefereeService {

    private RefereeRepository repository;

    @Autowired
    public RefereeService(RefereeRepository repository) {
        this.repository = repository;
    }

    public List<RefereeDto> getAllReferees() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertRefereeToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no referees in the database");
        }
    }

    public Optional<RefereeDto> getRefereeById(int id) {
        Optional<RefereeDto> newReferee = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Referee> referee = repository.findById(id);
            newReferee = convertRefereeToDto(referee);
        }
        return newReferee;
    }

    public Referee addNewReferee(RefereeDto refereeDto) {
        return repository.save(convertDtoToReferee(refereeDto));
    }

    public boolean updateRefereeById(RefereeDto refereeDto, int id) {
        if (checkIfIdExists(id)) {
            Referee updatedReferee = convertDtoToExistingReferee(refereeDto, repository.findById(id).get());
            repository.save(updatedReferee);
            return true;
        }
        return false;
    }

    public boolean deleteRefereeById(int id) {
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
        Optional<Referee> newReferee = repository.findById(id);
        if (newReferee.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private RefereeDto convertRefereeToDto(Referee referee) {
        return (RefereeDto) new DtoUtils().convertToDto(referee, new RefereeDto());
    }

    private Optional<RefereeDto> convertRefereeToDto(Optional<Referee> referee) {
        RefereeDto refereeDto = (RefereeDto) new DtoUtils().convertToDto(referee.get(), new RefereeDto());
        return Optional.of(refereeDto);
    }

    private Referee convertDtoToReferee(RefereeDto refereeDto){
        return (Referee) new DtoUtils().convertToEntity(new Referee(), refereeDto);
    }

    private Referee convertDtoToExistingReferee(RefereeDto refereeDto, Referee referee) {
        Referee newReferee = convertDtoToReferee(refereeDto);
        newReferee.setId(referee.getId());
        return newReferee;
    }

}
