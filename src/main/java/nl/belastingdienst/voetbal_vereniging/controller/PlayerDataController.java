package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDataDto;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.model.PlayerData;
import nl.belastingdienst.voetbal_vereniging.service.PlayerDataService;
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
public class PlayerDataController {

    private PlayerDataService service;

    @Autowired
    public PlayerDataController(PlayerDataService service) {
        this.service = service;
    }

    @GetMapping(value = "/players-data")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<List<PlayerDataDto>> getAllPlayersData() {
        List<PlayerDataDto> playerDataDtos = service.getAllPlayersData();
        if(playerDataDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerDataDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/player-data/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<PlayerDataDto> getPlayerDataById(@PathVariable int id) {
        Optional<PlayerDataDto> playerDataDto = service.getPlayerDataById(id);
        if (playerDataDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerDataDto.get(), HttpStatus.OK);
    }

    /*
    Get request to find a list of PlayerDataDtos that match a specific criteria from the @RequestBody
     */
    @GetMapping(value = "/trainer/players-scouting")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<List<PlayerDataDto>> getPlayersScouting(@RequestBody PlayerDataDto playerDataDto) {

        List<PlayerDataDto> playerDtos = service.getPlayersScoutingList(playerDataDto);
        if(playerDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerDtos, HttpStatus.OK);

    }

    @PostMapping(value = "/player-data")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> postPlayerData(@Valid @RequestBody PlayerDataDto playerDataDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        PlayerData playerData = service.addNewPlayerData(playerDataDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(playerData.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/player-data/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> putPlayerDataById(@Valid @RequestBody PlayerDataDto playerDataDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updatePlayerDataById(playerDataDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/player-data/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<PlayerDataDto> deletePlayerDataDto(@PathVariable int id) {
        if (service.deletePlayerDataById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
