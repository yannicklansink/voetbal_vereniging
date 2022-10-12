package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.repository.TrainerRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    private TrainerRepository repository;

    private TeamService teamService;

    @Autowired
    public TrainerService(TrainerRepository repository, TeamService teamService) {
        this.teamService = teamService;
        this.repository = repository;
    }

    public List<TrainerDto> getAllTrainers() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertTrainerToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no trainers in the database");
        }
    }

    public Optional<TrainerDto> getTrainerById(int id) {
        Optional<TrainerDto> newTrainer = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Trainer> trainer = repository.findById(id);
            newTrainer = convertTrainerToDto(trainer);
        }
        return newTrainer;
    }

    public Trainer addNewTrainer(TrainerDto trainerDto) {
        boolean checkIfIdOfTeamExists = false;
        if (trainerDto.getTeam() != null) {
            checkIfIdOfTeamExists = teamService.checkIfIdExists(trainerDto.getTeam().getId());
        }

        if (!checkIfIdOfTeamExists) {
            throw new RecordNotFoundException("Team id is not used");
        }
        return repository.save(convertDtoToTrainer(trainerDto));
    }

    public boolean updateTrainerById(TrainerDto trainerDto, int id) {
        boolean checkIfIdOfTeamExists = false;
        if (trainerDto.getTeam() != null) {
            checkIfIdOfTeamExists = teamService.checkIfIdExists(trainerDto.getTeam().getId());
        }

        if (!checkIfIdOfTeamExists) {
            throw new RecordNotFoundException("Team id is not used");
        }
        if (checkIfIdExists(id)) {
            Trainer updateTrainer = convertDtoToExistingTrainer(trainerDto, repository.findById(id).get());
            repository.save(updateTrainer);
            return true;
        }
        return false;
    }

    public boolean deleteTrainerById(int id) {
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
        Optional<Trainer> newTrainer = repository.findById(id);
        if (newTrainer.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private TrainerDto convertTrainerToDto(Trainer trainer) {
        return (TrainerDto) new DtoUtils().convertToDto(trainer, new TrainerDto());
    }

    private Optional<TrainerDto> convertTrainerToDto(Optional<Trainer> trainer) {
        TrainerDto trainerDto = (TrainerDto) new DtoUtils().convertToDto(trainer.get(), new TrainerDto());
        return Optional.of(trainerDto);
    }

    private Trainer convertDtoToTrainer(TrainerDto trainerDto){
        return (Trainer) new DtoUtils().convertToEntity(new Trainer(), trainerDto);
    }

    private Trainer convertDtoToExistingTrainer(TrainerDto trainerDto, Trainer trainer) {
        Trainer newTrainer = convertDtoToTrainer(trainerDto);
        newTrainer.setId(trainer.getId());
        return newTrainer;
    }

}
