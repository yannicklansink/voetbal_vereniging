package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.TrainerDto;
import nl.belastingdienst.voetbal_vereniging.model.Trainer;
import nl.belastingdienst.voetbal_vereniging.service.TrainerService;
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
public class TrainerController {

    private TrainerService service;

    @Autowired
    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @GetMapping(value = "/trainers")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        List<TrainerDto> trainerDtos = service.getAllTrainers();
        if(trainerDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trainerDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/trainer/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<TrainerDto> getTrainerById(@PathVariable int id) {
        Optional<TrainerDto> trainerDto = service.getTrainerById(id);
        if (trainerDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainerDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/trainer")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> postTrainer(@Valid @RequestBody TrainerDto trainerDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Trainer trainer = service.addNewTrainer(trainerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(trainer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/trainer/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> putTrainerById(@Valid @RequestBody TrainerDto trainerDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateTrainerById(trainerDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/trainer/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<TrainerDto> deleteTrainer(@PathVariable int id) {
        if (service.deleteTrainerById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
