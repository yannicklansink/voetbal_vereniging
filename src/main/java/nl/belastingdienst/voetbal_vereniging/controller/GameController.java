package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.GameDto;
import nl.belastingdienst.voetbal_vereniging.model.Game;
import nl.belastingdienst.voetbal_vereniging.service.GameService;
import nl.belastingdienst.voetbal_vereniging.util.BindingResultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GameController {

    private GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping(value = "/games")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> gameDtos = service.getAllGames();
        if(gameDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gameDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/game/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<GameDto> getGameById(@PathVariable int id) {
        Optional<GameDto> gameDto = service.getGameById(id);
        if (gameDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/game")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> postGame(@Valid @RequestBody GameDto gameDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Game game = service.addNewGame(gameDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/game/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> putGameById(@Valid @RequestBody GameDto gameDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateGameById(gameDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/game/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<GameDto> deleteGame(@PathVariable int id) {
        if (service.deleteGameById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
