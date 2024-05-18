package com.unicalsocial.backend.user;


import java.util.Collection;

public interface UserService {
    Collection<UserResponse> getAllUser();
    Collection<UserResponse> getAllUserOrderedBySignUpDate();
    UserResponse getUserById(long id);
    UserResponse getUserByUsername(String username);
    Collection<UserResponse> getUserLikeUsername(String username,int page);
    Long countAllUsersLikeUsername(String username);
}
