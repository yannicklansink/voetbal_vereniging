package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.model.Speler;
import nl.belastingdienst.voetbal_vereniging.service.SpelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpelerController {

    private SpelerService service;

    @Autowired
    public SpelerController(SpelerService service) {
        this.service = service;
    }

    public SpelerController() {
    }

    @GetMapping(value = "/spelers")
    public List<Speler> getAllSpelers() {
        return service.getAllSpelers();
    }

    @GetMapping(value = "/speler/{id}")
    public Optional<Speler> getSpelerById(@PathVariable int id) {
        return service.getSpelerById(id);
    }

    @PostMapping(value = "/speler")
    public void postSpeler(@RequestBody Speler speler) {
        service.addNewSpeler(speler);
    }

    @PutMapping(value = "/speler/{id}")
    public ResponseEntity<Object> putSpelerById(@RequestBody Speler speler, @PathVariable int id) {
        service.updateSpelerById(speler);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/speler/{id}")
    public void deleteSpeler(@PathVariable int id) {
        service.deleteSpelerById(id);
    }


}
