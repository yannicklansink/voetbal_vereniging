package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.GameDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
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

    @Autowired
    public GameService(GameRepository repository) {
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
        return repository.save(convertDtoToGame(gameDto));
    }

    public boolean updateGameById(GameDto gameDto, int id) {
        if (checkIfIdExists(id)) {
            Game updatedGame = convertDtoToExistingGame(gameDto, repository.findById(id).get());
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
