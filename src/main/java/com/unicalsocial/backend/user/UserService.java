package com.unicalsocial.backend.user;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<UserDTO>> getAllUser();
    ResponseEntity<List<UserDTO>> getAllUserOrderedBySignUpDate();
    ResponseEntity<UserDTO> getUserById(int id);
    ResponseEntity<UserDTO> createUser(UserDTO userDTO);
}
