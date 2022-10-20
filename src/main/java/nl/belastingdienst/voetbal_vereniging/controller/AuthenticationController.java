package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.AuthenticationRequest;
import nl.belastingdienst.voetbal_vereniging.dto.AuthenticationResponse;
import nl.belastingdienst.voetbal_vereniging.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class AuthenticationController {

    UserAuthenticateService userAuthenticateService;

    @Autowired
    public AuthenticationController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequestDto) {
        AuthenticationResponse authenticationResponse = userAuthenticateService.authenticateUser(authenticationRequestDto);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping(value = "/authenticated")
    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }


}
