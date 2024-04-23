package com.unicalsocial.backend.user;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    List<UserDTO> getAllUserOrderedBySignUpDate(UserDTO userDTO);
    UserDTO getUserById(int id);
    UserDTO createUser(UserDTO userDTO);
}
