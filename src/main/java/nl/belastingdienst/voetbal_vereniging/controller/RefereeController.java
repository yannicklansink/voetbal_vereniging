package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.RefereeDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.model.Referee;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.service.RefereeService;
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
public class RefereeController {

    private RefereeService service;

    @Autowired
    public RefereeController(RefereeService service) {
        this.service = service;
    }

    @GetMapping(value = "/referees")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<List<RefereeDto>> getAllReferees() {
        List<RefereeDto> refereeDtos = service.getAllReferees();
        if(refereeDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(refereeDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/referee/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<RefereeDto> getRefereeById(@PathVariable int id) {
        Optional<RefereeDto> refereeDto = service.getRefereeById(id);
        if (refereeDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(refereeDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/referee")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> postReferee(@Valid @RequestBody RefereeDto refereeDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Referee referee = service.addNewReferee(refereeDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(referee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/referee/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> putRefereeById(@Valid @RequestBody RefereeDto refereeDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateRefereeById(refereeDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/referee/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<RefereeDto> deleteReferee(@PathVariable int id) {
        if (service.deleteRefereeById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
