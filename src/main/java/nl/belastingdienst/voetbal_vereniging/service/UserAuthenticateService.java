package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.AuthenticationRequest;
import nl.belastingdienst.voetbal_vereniging.dto.AuthenticationResponse;
import nl.belastingdienst.voetbal_vereniging.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserAuthenticateService {

    @Autowired
    JwtUtil jwtUtl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequestDto) {

        String user = authenticationRequestDto.getUsername();
        String pass = authenticationRequestDto.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Incorrect username and/or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user);
        return new AuthenticationResponse(jwtUtl.generateToken(userDetails));
    }

}
