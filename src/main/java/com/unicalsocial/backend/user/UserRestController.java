package com.unicalsocial.backend.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        var users = this.userService.getAllUser();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        Optional<UserDTO> userDTO = Optional.ofNullable(userService.getUserById(id));
        return userDTO.map(s -> ResponseEntity.ok().body(s)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/users/save")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            var saved = this.userService.createUser(userDTO);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
