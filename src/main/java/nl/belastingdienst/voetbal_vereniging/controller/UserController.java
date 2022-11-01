package nl.belastingdienst.voetbal_vereniging.controller;

import nl.belastingdienst.voetbal_vereniging.dto.UserPostRequestDto;
import nl.belastingdienst.voetbal_vereniging.exception.BadRequestException;
import nl.belastingdienst.voetbal_vereniging.model.User;
import nl.belastingdienst.voetbal_vereniging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @GetMapping(value = "/{username}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(service.getUser(username));
    }

    @PostMapping(value = "")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequestDto user) {
        String newUsername = service.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        service.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        service.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(service.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> field) {
        try {
            String authorityName = (String) field.get("authority");
            service.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }


}
