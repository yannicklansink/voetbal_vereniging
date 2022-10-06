package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.UserDto;
import nl.belastingdienst.voetbal_vereniging.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUser(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("User not found in database");
        }

        String password = userDto.getPassword();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userDto.getAuthorities().forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority.getUsername()));
        });
        return new org.springframework.security.core.userdetails.User(username, password, authorities);

//        Set<Authority> authorities = userDto.getAuthorities();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority: authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }
}
