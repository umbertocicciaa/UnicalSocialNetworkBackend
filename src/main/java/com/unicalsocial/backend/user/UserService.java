package com.unicalsocial.backend.user;


import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface UserService {
    Collection<UserResponse> getAllUser();
    Collection<UserResponse> getAllUserOrderedBySignUpDate();
    UserResponse getUserById(long id);
    UserResponse getUserByUsername(String username);
    Collection<UserResponse> getUserLikeUsername(String username,int page);
    UserCountResponse countAllUsersLikeUsername(String username);
    UserResponse getLoggedUser(Authentication authentication);
    UserResponse updateUser(UpdateUserRequest userUpdateRequest, Authentication authentication);
}
