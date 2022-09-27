package nl.belastingdienst.voetbal_vereniging.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class BindingResultValidation {
    public static ResponseEntity<String> fieldErrors(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for(FieldError error : br.getFieldErrors()){
            sb.append(error.getField() + ": ");
            sb.append(error.getDefaultMessage());
            sb.append("\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

}
