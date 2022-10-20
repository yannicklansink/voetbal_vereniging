package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.TeamPlayersDto;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.service.TeamService;
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
public class TeamController {

    private TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping(value = "/teams")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<List<TeamPlayersDto>> getAllTeams() {
        List<TeamPlayersDto> teamPlayersDto = service.getAllTeams();
        if(teamPlayersDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teamPlayersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<TeamPlayersDto> getTeamById(@PathVariable int id) {
        Optional<TeamPlayersDto> teamPlayersDto = service.getTeamDtoById(id);
        if (teamPlayersDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamPlayersDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/team")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> postTeam(@Valid @RequestBody TeamPlayersDto teamPlayersDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Team team = service.addNewTeam(teamPlayersDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(team.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/team/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> putTeamById(@Valid @RequestBody TeamPlayersDto teamPlayersDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateTeamById(teamPlayersDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/team/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<TeamPlayersDto> deleteTeam(@PathVariable int id) {
        if (service.deleteTeamById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
