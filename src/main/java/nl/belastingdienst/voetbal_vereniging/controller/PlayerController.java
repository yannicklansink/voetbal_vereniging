package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.service.PlayerService;
import nl.belastingdienst.voetbal_vereniging.util.BindingResultValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    public PlayerController() {
    }

    @GetMapping(value = "/players")
    public ResponseEntity<List<DtoEntity>> getAllPlayers() {
        List<DtoEntity> playerDtos = service.getAllSpelers();
        if(playerDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/player/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable int id) {
        Optional<PlayerDto> spelerDto = service.getPlayerDtoById(id);
        if (spelerDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spelerDto.get(), HttpStatus.OK);
    }



    // @Valid: When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    @PostMapping(value = "/player")
    public ResponseEntity<String> postPlayer(@Valid @RequestBody PlayerDto playerDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Player player = service.addNewSpeler(playerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(player.getPlayerId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/player/{id}")
    public ResponseEntity<String> putPlayerById(@Valid @RequestBody PlayerDto playerDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updatePlayerById(playerDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/player/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable int id) {
        if (service.deleteSpelerById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
