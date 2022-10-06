package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.DtoEntity;
import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainingDto;
import nl.belastingdienst.voetbal_vereniging.model.Player;
import nl.belastingdienst.voetbal_vereniging.model.Training;
import nl.belastingdienst.voetbal_vereniging.service.TrainingService;
import nl.belastingdienst.voetbal_vereniging.util.BindingResultValidation;
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
public class TrainingController {

    private TrainingService service;

    @Autowired
    public TrainingController(TrainingService service) {
        this.service = service;
    }

    @GetMapping(value = "/trainings")
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> trainingDtos = service.getAllTrainings();
        if(trainingDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(trainingDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/training/{id}")
    public ResponseEntity<TrainingDto> getTrainingById(@PathVariable int id) {
        Optional<TrainingDto> trainingDto = service.getTrainingById(id);
        if (trainingDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingDto.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/training")
    public ResponseEntity<String> postTraining(@Valid @RequestBody TrainingDto trainingDto, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        Training training = service.addNewTraining(trainingDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(training.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/training/{id}")
    public ResponseEntity<String> putTrainingById(@Valid @RequestBody TrainingDto trainingDto, @PathVariable int id, BindingResult br) {
        if(br.hasErrors()){
            return BindingResultValidation.fieldErrors(br);
        }
        service.updateTrainingById(trainingDto, id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/training/{id}")
    public ResponseEntity<TrainingDto> deleteTraining(@PathVariable int id) {
        if (service.deleteTrainingById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}





