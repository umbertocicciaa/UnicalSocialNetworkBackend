package com.unicalsocial.backend.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/User")
@Tag(name="User")
public class UserRestController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserResponse>> getUsers() {
        var users= this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUsersById(@PathVariable int id) {
        return ResponseEntity.ok(this.userService.getUserById(id));

    }

    @CrossOrigin
    @GetMapping(value = "/users/username")
    public ResponseEntity<Collection<UserResponse>> getUserLikeUsername(@RequestParam("username") String username, @RequestParam(defaultValue = "0")int page) {
        return ResponseEntity.ok(this.userService.getUserLikeUsername(username,page));
    }

    @CrossOrigin
    @GetMapping(value = "/users/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }

    @CrossOrigin
    @GetMapping(value = "/ordered-users")
    public ResponseEntity<Collection<UserResponse>> getUsersOrderedBySignUp() {
        return ResponseEntity.ok(this.userService.getAllUserOrderedBySignUpDate());
    }


    @CrossOrigin
    @GetMapping(value = "/total-users")
    public ResponseEntity<Long> getTotalUsers(@RequestParam("username")String username) {
        return ResponseEntity.ok(this.userService.countAllUsersLikeUsername(username));
    }


}
