package nl.belastingdienst.voetbal_vereniging.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.voetbal_vereniging.model.Authority;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto implements DtoEntity {

    public String username;

    public String password;
    public Boolean enabled;
    public String email;
    @JsonSerialize
    public Set<Authority> authorities;

}
