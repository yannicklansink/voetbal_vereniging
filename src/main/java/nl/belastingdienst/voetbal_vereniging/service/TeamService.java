package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.TeamPlayersDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.ForeignKeyFoundException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Team;
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

    public List<TeamPlayersDto> getAllTeams() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertTeamToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no teams in the database");
        }
    }

    public Optional<TeamPlayersDto> getTeamDtoById(int id) {
        Optional<TeamPlayersDto> newTeamPlayersDto = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Team> team = repository.findById(id);
            newTeamPlayersDto = convertTeamToDto(team);
        }
        return newTeamPlayersDto;
    }

    public Optional<Team> getTeamById(int id) {
        Optional<Team> team = repository.findById(id);
        return team;
    }

    public Team addNewTeam(TeamPlayersDto teamPlayersDto) {
        return repository.save(convertDtoToTeam(teamPlayersDto));
    }

    public boolean updateTeamById(TeamPlayersDto teamPlayersDto, int id) {
        if (checkIfIdExists(id)) {
            Team updatedTeam = convertDtoToExistingTeam(teamPlayersDto, repository.findById(id).get());
            repository.save(updatedTeam);
            return true;
        }
        return false;
    }

    /*
    Team will only be deleted if the foreign key is not used in another table
     */
    public boolean deleteTeamById(int id) {
        if (checkIfIdExists(id)) {
            try {
                repository.deleteById(id);
                return true;
            } catch (Exception e) {
                throw new ForeignKeyFoundException("You can not delete a team when it has foreign key(s) attached");
            }
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
    private TeamPlayersDto convertTeamToDto(Team team) {
        TeamPlayersDto returnTeamDto = (TeamPlayersDto) new DtoUtils().convertToDto(team, new TeamPlayersDto());
        return returnTeamDto;
    }

    private Optional<TeamPlayersDto> convertTeamToDto(Optional<Team> team) {
        TeamPlayersDto teamPlayersDto = (TeamPlayersDto) new DtoUtils().convertToDto(team.get(), new TeamPlayersDto());
        return Optional.of(teamPlayersDto);
    }

    private Team convertDtoToTeam(TeamPlayersDto teamPlayersDto){
        return (Team) new DtoUtils().convertToEntity(new Team(), teamPlayersDto);
    }

    private Team convertDtoToExistingTeam(TeamPlayersDto teamPlayersDto, Team team) {
        Team newTeam = convertDtoToTeam(teamPlayersDto);
        newTeam.setId(team.getId());
        return newTeam;
    }

    /*
        Returns true when team name is in database
     */
    public List<Team> doesTeamNameExists(String teamName) {
        List<Team> teamList = repository.findTeamByTeamName(teamName);
        return teamList;
    }

    public int getTeamIdFromName(String teamName) {
        String uppercaseRefereeName = teamName.substring(0, 1).toUpperCase() + teamName.substring(1);
        List<Integer> validName = repository.findTeamIdByTeamName(uppercaseRefereeName);
        if (validName.size() > 0 ) {
            return validName.get(0);
        } else {
            throw new BadRequestException("Your request for team name is not valid");
        }
    }
}
