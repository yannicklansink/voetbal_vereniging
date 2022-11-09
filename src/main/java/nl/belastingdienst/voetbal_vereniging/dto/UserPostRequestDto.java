package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserPostRequestDto {

    private String username;
    private String password;
    private String email;
    private Set<String> authorities;

}
