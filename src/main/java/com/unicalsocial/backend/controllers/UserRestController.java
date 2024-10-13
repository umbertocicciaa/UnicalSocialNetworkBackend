package com.unicalsocial.backend.controllers;

import com.unicalsocial.backend.dtos.UpdateUserRequest;
import com.unicalsocial.backend.dtos.UserCountResponse;
import com.unicalsocial.backend.dtos.UserResponse;
import com.unicalsocial.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/User")
@Tag(name="User")
@SecurityRequirement(name = "Bearer Authentication")
public class UserRestController {

    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
    @GetMapping(value = "/users")
    public ResponseEntity<Collection<UserResponse>> getUsers() {
        var users= this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }


    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUsersById(@PathVariable int id) {
        return ResponseEntity.ok(this.userService.getUserById(id));

    }


    @GetMapping(value = "/users/username")
    public ResponseEntity<Collection<UserResponse>> getUserLikeUsername(@RequestParam("username") String username, @RequestParam(defaultValue = "0")int page) {
        return ResponseEntity.ok(this.userService.getUserLikeUsername(username,page));
    }


    @GetMapping(value = "/users/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }


    @GetMapping(value = "/ordered-users")
    public ResponseEntity<Collection<UserResponse>> getUsersOrderedBySignUp() {
        return ResponseEntity.ok(this.userService.getAllUserOrderedBySignUpDate());
    }


    @PutMapping(value = "/users/username")
    public ResponseEntity<UserResponse> updateProfileUser(@RequestBody UpdateUserRequest userUpdateRequest, Authentication authentication){
        return ResponseEntity.ok(this.userService.updateUser(userUpdateRequest,authentication));
    }


    @GetMapping(value = "/total-users")
    public ResponseEntity<UserCountResponse> getTotalUsers(@RequestParam("username")String username) {
        return ResponseEntity.ok(this.userService.countAllUsersLikeUsername(username));
    }


    @GetMapping(value = "/logged-users")
    public ResponseEntity<UserResponse> getLoggedUsers(Authentication authentication) {
        return ResponseEntity.ok(this.userService.getLoggedUser(authentication));
    }

}
