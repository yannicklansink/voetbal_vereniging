package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {

    // moet niet elke methode een ResponseEntity<> terug geven?
    private PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    public PlayerController() {
    }

    @GetMapping(value = "/players")
    public ResponseEntity<List<DtoEntity>> getAllSpelers() {
        List<DtoEntity> playerDtos = service.getAllSpelers();
        if(playerDtos.isEmpty()){
            // optional to use HttpStatus.NOT_FOUND as response code
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/player/{id}")
    public ResponseEntity<PlayerDto> getSpelerById(@PathVariable int id) {
//        return service.getSpelerById(id);
        System.out.println("Fetching player with id " + id);
        Optional<PlayerDto> spelerDto = service.getSpelerById(id);
        if (spelerDto.isEmpty()) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spelerDto.get(), HttpStatus.OK);
    }

    // VRAAG: moet de gebruiker bij een POST-request een DTO of model meegeven?
    // @Valid: When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    @PostMapping(value = "/player")
    public ResponseEntity<String> postSpeler(@Valid @RequestBody PlayerDto playerDto, BindingResult br) {
        System.out.println("Creating player " + playerDto.getPlayerName());
        StringBuilder sb = new StringBuilder();
        if(br.hasErrors()){
            for(FieldError error : br.getFieldErrors()){
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Player player = service.addNewSpeler(playerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(player.getPlayerId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/player/{id}")
    public ResponseEntity<String> putSpelerById(@Valid @RequestBody PlayerDto playerDto, @PathVariable int id, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if(br.hasErrors()){
            for(FieldError error : br.getFieldErrors()){
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        System.out.println("Updating Player " + id);
        service.updatePlayerById(playerDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/player/{id}")
    public ResponseEntity<PlayerDto> deleteSpeler(@PathVariable int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        if (service.deleteSpelerById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        System.out.println("Unable to delete. User with id " + id + " not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
