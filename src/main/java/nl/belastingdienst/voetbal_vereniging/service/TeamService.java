package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.TeamDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.repository.TeamRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<TeamDto> getAllTeams() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no teams in the database");
        }
    }

    public Optional<TeamDto> getTeamById(int id) {
        Optional<TeamDto> newTeam = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Team> team = repository.findById(id);
            newTeam = convertTeamToDto(team);
        }
        return newTeam;
    }

    public Team addNewTeam(TeamDto teamDto) {
        return repository.save(convertDtoToTeam(teamDto));
    }

    public boolean updateTeamById(TeamDto teamDto, int id) {
        if (checkIfIdExists(id)) {
            Team updatedTeam = convertDtoToExistingTeam(teamDto, repository.findById(id).get());
            repository.save(updatedTeam);
            return true;
        }
        return false;
    }

    public boolean deleteTeamById(int id) {
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
        Optional<Team> newTeam = repository.findById(id);
        if (newTeam.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private TeamDto convertTeamToDto(Team team) {
        return (TeamDto) new DtoUtils().convertToDto(team, new TeamDto());
    }

    private Optional<TeamDto> convertTeamToDto(Optional<Team> team) {
        TeamDto teamDto = (TeamDto) new DtoUtils().convertToDto(team.get(), new TeamDto());
        return Optional.of(teamDto);
    }

    private Team convertDtoToTeam(TeamDto teamDto){
        return (Team) new DtoUtils().convertToEntity(new Team(), teamDto);
    }

    private Team convertDtoToExistingTeam(TeamDto teamDto, Team team) {
        Team newTeam = convertDtoToTeam(teamDto);
        newTeam.setId(team.getId());
        return newTeam;
    }

}
