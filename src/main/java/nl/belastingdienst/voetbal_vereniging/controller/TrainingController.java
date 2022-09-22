package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.PlayerDto;
import nl.belastingdienst.voetbal_vereniging.dto.TrainingDto;
import nl.belastingdienst.voetbal_vereniging.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            // optional to use HttpStatus.NOT_FOUND as response code
            return new ResponseEntity<List<TrainingDto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(trainingDtos, HttpStatus.OK);
    }

}





