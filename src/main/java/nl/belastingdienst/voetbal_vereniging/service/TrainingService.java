package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.TrainingDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Training;
import nl.belastingdienst.voetbal_vereniging.repository.TrainingRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private TrainingRepository repository;

    private TeamService teamService;

    @Autowired
    public TrainingService(TrainingRepository repository, TeamService teamService) {
        this.teamService = teamService;
        this.repository = repository;
    }

    public List<TrainingDto> getAllTrainings() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertTrainingToDto)
                    .collect(Collectors.toList());
        } else {
            throw new RecordNotFoundException("There are no trainings in the database");
        }
    }

    public Optional<TrainingDto> getTrainingById(int id) {
        Optional<TrainingDto> newTraining = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Training> training = repository.findById(id);
            newTraining = convertTrainingToDto(training);
        }
        return newTraining;
    }

    public List<TrainingDto> getTrainingsByTeamId(int teamid) {
        List<Training> trainingList = repository.findAllByTeamId(teamid);
        List<TrainingDto> trainingDtoList = new ArrayList<>();
        for (Training training : trainingList) {
            trainingDtoList.add(convertTrainingToDto(training));
            System.out.println("team names: " + training.getTeam().getTeamName());
        }
        return trainingDtoList;
    }

    public Training addNewTraining(TrainingDto trainingDto) {
        boolean checkIfIdOfTeamExists = false;
        if (trainingDto.getTeam() != null) {
            checkIfIdOfTeamExists = teamService.checkIfIdExists(trainingDto.getTeam().getId());
        }

        if (!checkIfIdOfTeamExists) {
            throw new RecordNotFoundException("Team id is not used");
        }
        return repository.save(convertDtoToTraining(trainingDto));
    }

    public boolean updateTrainingById(TrainingDto trainingDto, int id) {
        if (checkIfIdExists(id)) {
            boolean checkIfIdOfTeamExists = false;
            if (trainingDto.getTeam() != null) {
                checkIfIdOfTeamExists = teamService.checkIfIdExists(trainingDto.getTeam().getId());
            }

            if (!checkIfIdOfTeamExists) {
                throw new RecordNotFoundException("Team id is not used");
            }
            Training updateTrainer = convertDtoToExistingTraining(trainingDto, repository.findById(id).get());
            repository.save(updateTrainer);
            return true;
        }
        return false;
    }

    public boolean deleteTrainingById(int id) {
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
        Optional<Training> newTraining = repository.findById(id);
        if (newTraining.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private TrainingDto convertTrainingToDto(Training training) {

        return (TrainingDto) new DtoUtils().convertToDto(training, new TrainingDto());
    }

    private Optional<TrainingDto> convertTrainingToDto(Optional<Training> training) {
        TrainingDto trainingDto = (TrainingDto) new DtoUtils().convertToDto(training.get(), new TrainingDto());
        return Optional.of(trainingDto);
    }

    private Training convertDtoToTraining(TrainingDto trainingDto){
        return (Training) new DtoUtils().convertToEntity(new Training(), trainingDto);
    }

    private Training convertDtoToExistingTraining(TrainingDto trainingDto, Training training) {
        Training newTraining = convertDtoToTraining(trainingDto);
        newTraining.setId(training.getId());
        return newTraining;
    }


}
