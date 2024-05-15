package com.unicalsocial.backend.user;

import com.unicalsocial.backend.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}"+"User")
@Tag(name="User")
public class UserRestController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserDTO>> getUsers() {
        var users= this.userService.getAllUser();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.createUser(userDTO));
    }

    @CrossOrigin
    @GetMapping(value = "/users/id/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(this.userService.getUserById(id));
        }catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping(value = "/users/username")
    public ResponseEntity<Collection<UserDTO>> getUserLikeUsername(@RequestParam("username") String username, @RequestParam(defaultValue = "0")int page) {
        var users= this.userService.getUserLikeUsername(username,page);
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @GetMapping(value = "/users/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(this.userService.getUserByUsername(username));
        }catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping(value = "/ordered-users")
    public ResponseEntity<Collection<UserDTO>> getUsersOrderedBySignUp() {
        var users=this.userService.getAllUserOrderedBySignUpDate();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }


    @CrossOrigin
    @GetMapping(value = "/total-users")
    public ResponseEntity<Long> getTotalUsers(@RequestParam("username")String username) {
        return ResponseEntity.ok(this.userService.countAllUsersLikeUsername(username));
    }


}
