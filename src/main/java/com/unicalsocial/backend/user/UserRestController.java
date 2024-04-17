package com.unicalsocial.backend.user;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<UserDTO> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/users/{id}")
    public UserDTO getUsersById(@PathVariable int id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null)
            throw new ResourceNotFoundException();
        return userDTO;
    }

    @PostMapping(value = "/users/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
