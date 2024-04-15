package com.unicalsocial.backend.controllers;

import com.unicalsocial.backend.dto.UserDTO;
import com.unicalsocial.backend.services.UserService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint}" + "users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/**")
    public List<UserDTO> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUsersById(@PathVariable int id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO == null)
            throw new ResourceNotFoundException();
        return userDTO;
    }

    @PostMapping(value = "/save", consumes = "application/json")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
