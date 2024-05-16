package com.unicalsocial.backend.user;


import java.util.Collection;

public interface UserService {
    Collection<UserDTO> getAllUser();
    Collection<UserDTO> getAllUserOrderedBySignUpDate();
    UserDTO getUserById(long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByUsername(String username);
    Collection<UserDTO> getUserLikeUsername(String username,int page);
    Long countAllUsersLikeUsername(String username);
}
