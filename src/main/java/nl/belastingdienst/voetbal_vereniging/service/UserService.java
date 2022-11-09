package nl.belastingdienst.voetbal_vereniging.service;

import nl.belastingdienst.voetbal_vereniging.dto.UserPostRequestDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.model.Authority;
import nl.belastingdienst.voetbal_vereniging.model.User;
import nl.belastingdienst.voetbal_vereniging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository repository;

    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Optional<User> getUser(String user) {
        return repository.findById(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public String createUser(UserPostRequestDto userPost) {
        try {
            String newEncryptedPassword = encoder.encode(userPost.getPassword());

            User user = new User(
                    userPost.getUsername(),
                    newEncryptedPassword,
                    userPost.getEmail(),
                    true,
                    "ROLE_USER"
            );

            for (String authority : userPost.getAuthorities()) {
                if (!authority.startsWith("ROLE_")) {
                    authority = "ROLE_" + authority;
                }
                authority = authority.toUpperCase();
                if (!authority.equals("ROLE_USER")) {
                    user.addAuthority(authority);
                }
            }

            User newUser = repository.save(user);
            return newUser.getUsername();
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create your requested user.");
        }

    }

    public void deleteUser(String username) {
        if (repository.existsById(username)) {
            repository.deleteById(username);
        } else {
            throw new RecordNotFoundException("User not found for: " + username);
        }
    }

    public void updateUser(String userName, User newUser) {
        Optional<User> optionalUser = repository.findById(userName);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found for: " + userName);
        } else {
            User user = optionalUser.get();
            user.setPassword(encoder.encode(newUser.getPassword()));
            user.setEnabled(newUser.isEnabled());
            user.setEmail(newUser.getEmail());
            repository.save(user);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        Optional<User> optionalUser = repository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found for: " + username);
        } else {
            User newUser = optionalUser.get();
            return newUser.getAuthorities();
        }
    }

    public void addAuthority(String username, String newAuthority) {
        Optional<User> optionalUser= repository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found for: " + username);
        } else {
            User user = optionalUser.get();
            user.addAuthority(newAuthority);
            repository.save(user);
        }
    }

}
