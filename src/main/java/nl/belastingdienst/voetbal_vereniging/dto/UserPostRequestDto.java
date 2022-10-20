package nl.belastingdienst.voetbal_vereniging.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserPostRequestDto {
    // Class used to create a new user

    private String username;
    private String password;
    private String email;
    private Set<String> authorities;

}
