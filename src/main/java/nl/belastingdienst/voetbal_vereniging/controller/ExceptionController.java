package nl.belastingdienst.voetbal_vereniging.controller;

import io.jsonwebtoken.ExpiredJwtException;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.BadTeamNameException;
import nl.belastingdienst.voetbal_vereniging.exception.ForeignKeyFoundException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ForeignKeyFoundException.class)
    public ResponseEntity<Object> exception(ForeignKeyFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadTeamNameException.class)
    public ResponseEntity<Object> exception(BadTeamNameException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> exception(AccessDeniedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> exception(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> exception(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }


}
