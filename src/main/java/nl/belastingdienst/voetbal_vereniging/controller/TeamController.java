package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.TeamDto;
import nl.belastingdienst.voetbal_vereniging.model.Team;
import nl.belastingdienst.voetbal_vereniging.service.TeamService;
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
public class TeamController {

    private TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping(value = "/teams")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teamDtos = service.getAllTeams();
        if(teamDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teamDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable int id) {
        Optional<TeamDto> teamDto = service.getTeamDtoById(id);
        if (teamDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/team")
    public ResponseEntity<String> postTeam(@Valid @RequestBody TeamDto teamDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Team team = service.addNewTeam(teamDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(team.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/team/{id}")
    public ResponseEntity<String> putTeamById(@Valid @RequestBody TeamDto teamDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateTeamById(teamDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/team/{id}")
    public ResponseEntity<TeamDto> deleteTeam(@PathVariable int id) {
        if (service.deleteTeamById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
