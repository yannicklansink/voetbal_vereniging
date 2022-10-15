package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.RefereeDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.ForeignKeyFoundException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.repository.RefereeRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Referee> getRefereeDtoById(int refereeId) {
        Optional<Referee> newReferee = Optional.empty();
        if (checkIfIdExists(refereeId)) {
            newReferee = repository.findById(refereeId);
        }
        return newReferee;
    }

    /*
    Actor can't create games from posting a referee
    returns a new Referee
     */
    public Referee addNewReferee(RefereeDto refereeDto) {
        refereeDto.setGames(null);
        return repository.save(convertDtoToReferee(refereeDto));
    }

    public boolean updateRefereeById(RefereeDto refereeDto, int id) {
        if (checkIfIdExists(id)) {
            refereeDto.setGames(null);
            Referee updatedReferee = convertDtoToExistingReferee(refereeDto, repository.findById(id).get());
            repository.save(updatedReferee);
            return true;
        }
        return false;
    }

    /*
    Referee will only be deleted if the foreign key is not used in another table
     */
    public boolean deleteRefereeById(int id) {
        if (checkIfIdExists(id)) {
            try {
                repository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new ForeignKeyFoundException("You can not delete a referee when it has foreign key(s) attached");
            }
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

    public Optional<RefereeDto> convertRefereeToDto(Optional<Referee> referee) {
        if (referee.isPresent()) {
            RefereeDto refereeDto = (RefereeDto) new DtoUtils().convertToDto(referee.get(), new RefereeDto());
            return Optional.of(refereeDto);
        }
        throw new RecordNotFoundException("Referee is not present");
    }

    private Referee convertDtoToReferee(RefereeDto refereeDto){
        return (Referee) new DtoUtils().convertToEntity(new Referee(), refereeDto);
    }

    public Referee convertDtoToExistingReferee(RefereeDto refereeDto, Referee referee) {
        Referee newReferee = convertDtoToReferee(refereeDto);
        newReferee.setId(referee.getId());
        return newReferee;
    }

    public int getRefereeIdFromName(String refereeName) {
        String uppercaseRefereeName = refereeName.substring(0, 1).toUpperCase() + refereeName.substring(1);
        List<Integer> validName = repository.findRefereeByRefereeName(uppercaseRefereeName);
        if (validName.size() > 0 ) {
            return validName.get(0);
        } else {
            throw new BadRequestException("Your request for referee name is not valid");
        }

    }


}
