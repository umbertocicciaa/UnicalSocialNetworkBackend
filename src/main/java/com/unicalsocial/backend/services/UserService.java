package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    UserDTO getUserById(int id);
}
