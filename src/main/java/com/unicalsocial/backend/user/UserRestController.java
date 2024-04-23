package com.unicalsocial.backend.user;

import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.endpoint}")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        var users = this.userService.getAllUser();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(users);
    }

    @CrossOrigin
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        Optional<UserDTO> userDTO = Optional.ofNullable(userService.getUserById(id));
        return userDTO.map(s -> ResponseEntity.ok().body(s)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping(value = "/users/save")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            var saved = this.userService.createUser(userDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
