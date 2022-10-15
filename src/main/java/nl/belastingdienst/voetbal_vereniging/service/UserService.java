package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.UserDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Authority;
import nl.belastingdienst.voetbal_vereniging.model.User;
import nl.belastingdienst.voetbal_vereniging.repository.UserRepository;
import nl.belastingdienst.voetbal_vereniging.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository repository;

//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService( UserRepository userRepository) {
        this.repository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

//    @Autowired
//    private AuthorityRepository authorityRepository;

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = repository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = repository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return repository.existsById(username);
    }

//    public String createUser(UserDto userDto) {
//        User newUser = repository.save(toUser(userDto));
//        return newUser.getUsername();
//    }

    public String createUser(UserDto userDto) {
        try {
            String encryptedPassword = SecurityFilter.passwordEncoder().encode(userDto.getPassword());

            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(userDto.getEmail());
            user.setEnabled(true);
            user.addAuthority(new Authority(userDto.getUsername(), "ROLE_USER"));
            for (Authority s : userDto.getAuthorities()) {
                if (!s.getAuthority().equals("ROLE_USER")) {
                    user.addAuthority(s);
                }
            }

            User newUser = repository.save(user);
            return newUser.getUsername();
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }
    }

    public void deleteUser(String username) {
        repository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!repository.existsById(username)) throw new RecordNotFoundException();
        User user = repository.findById(username).get();
        user.setPassword(newUser.getPassword());
        repository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!repository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        repository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!repository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        repository.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // moet de password niet worden encrypted?
//        String password = SecurityFilter.passwordEncoder().encode(userDto.getPassword());
//        user.setPassword(password);
        user.setEnabled(userDto.getEnabled());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
