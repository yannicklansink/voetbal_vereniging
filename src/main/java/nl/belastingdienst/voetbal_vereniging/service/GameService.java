package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.GameDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.repository.GameRepository;
import nl.belastingdienst.voetbal_vereniging.util.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private GameRepository repository;

    private RefereeService refereeService;

    private TeamService teamService;

    @Autowired
    public GameService(GameRepository repository, RefereeService refereeService, TeamService teamService) {
        this.refereeService = refereeService;
        this.teamService = teamService;
        this.repository = repository;
    }

    public List<GameDto> getAllGames() {
        if (repository.count() != 0) {
            return repository.findAll()
                    .stream()
                    .map(this::convertGameToDto)
                    .collect(Collectors.toList());

        } else {
            throw new RecordNotFoundException("There are no games in the database");
        }
    }

    public Optional<GameDto> getGameById(int id) {
        Optional<GameDto> newGame = Optional.empty();
        if (checkIfIdExists(id)) {
            Optional<Game> game = repository.findById(id);
            newGame = convertGameToDto(game);
        }
        return newGame;
    }

    public Game addNewGame(GameDto gameDto) {
        Optional<Referee> referee = Optional.empty();
        if (gameDto.getReferee() != null && gameDto.getReferee().getRefereeName() != null) {
            String refereeName = gameDto.getReferee().getRefereeName(); // this can be null
            int refereeId = refereeService.getRefereeIdFromName(refereeName);
            referee = refereeService.getRefereeDtoById(refereeId);
        }

        Optional<Team> team = Optional.empty();
        if (gameDto.getTeam() != null && gameDto.getTeam().getTeamName() != null) {
            String teamName = gameDto.getTeam().getTeamName();
            int teamNameId = teamService.getTeamIdFromName(teamName);
            team = teamService.getTeamById(teamNameId);

        }
        Game game = convertDtoToGame(gameDto);
        if (referee.isPresent() && team.isPresent()) {
            game.setReferee(referee.get());
            game.setTeam(team.get());
        } else {
            throw new RecordNotFoundException("You must specify the team name and the referee");
        }
        return repository.save(game);
    }

    public boolean updateGameById(GameDto gameDto, int id) {
        if (checkIfIdExists(id)) {

            Optional<Referee> referee = Optional.empty();
            if (gameDto.getReferee() != null && gameDto.getReferee().getRefereeName() != null) {
                String refereeName = gameDto.getReferee().getRefereeName(); // this can be null
                int refereeId = refereeService.getRefereeIdFromName(refereeName);
                referee = refereeService.getRefereeDtoById(refereeId);
            }

            Optional<Team> team = Optional.empty();
            if (gameDto.getTeam() != null && gameDto.getTeam().getTeamName() != null) {
                String teamName = gameDto.getTeam().getTeamName();
                int teamNameId = teamService.getTeamIdFromName(teamName);
                team = teamService.getTeamById(teamNameId);

            }
            Game updatedGame = convertDtoToExistingGame(gameDto, repository.findById(id).get());

            if (referee.isPresent() && team.isPresent()) {
                updatedGame.setReferee(referee.get());
                updatedGame.setTeam(team.get());
            } else {
                throw new RecordNotFoundException("You must specify the team name and the referee");
            }

            repository.save(updatedGame);
            return true;
        }
        return false;
    }

    public boolean deleteGameById(int id) {
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
        Optional<Game> newGame = repository.findById(id);
        if (newGame.isPresent()) {
            return true;
        } else {
            throw new RecordNotFoundException("Id is not in use");
        }
    }

    // Convert DTOs and Entities methodes
    private GameDto convertGameToDto(Game game) {
        return (GameDto) new DtoUtils().convertToDto(game, new GameDto());
    }

    private Optional<GameDto> convertGameToDto(Optional<Game> game) {
        GameDto gameDto = (GameDto) new DtoUtils().convertToDto(game.get(), new GameDto());
        return Optional.of(gameDto);
    }

    private Game convertDtoToGame(GameDto gameDto){
        return (Game) new DtoUtils().convertToEntity(new Game(), gameDto);
    }

    private Game convertDtoToExistingGame(GameDto gameDto, Game game) {
        Game newGame = convertDtoToGame(gameDto);
        newGame.setId(game.getId());
        return newGame;
    }


}
