package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class AuthenticationResponse {

    private final String jwt;

    public String getJwt() {
        return this.jwt;
    }
}
