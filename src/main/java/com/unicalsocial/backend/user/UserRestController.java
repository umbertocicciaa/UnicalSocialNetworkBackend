package com.unicalsocial.backend.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}"+"User")
public class UserRestController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return this.userService.getAllUser();
    }

    @CrossOrigin
    @GetMapping(value = "/usersOrdered")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<UserDTO>> getUsersOrderedBySignUp() {
        return this.userService.getAllUserOrderedBySignUpDate();
    }

    @CrossOrigin
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @CrossOrigin
    @PostMapping(value = "/users/save")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return this.userService.createUser(userDTO);
    }
}
