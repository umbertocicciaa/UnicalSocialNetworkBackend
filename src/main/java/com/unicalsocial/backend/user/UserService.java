package com.unicalsocial.backend.user;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface UserService {
    ResponseEntity<Collection<UserDTO>> getAllUser();
    ResponseEntity<Collection<UserDTO>> getAllUserOrderedBySignUpDate();
    ResponseEntity<UserDTO> getUserById(int id);
    ResponseEntity<UserDTO> createUser(UserDTO userDTO);
}
