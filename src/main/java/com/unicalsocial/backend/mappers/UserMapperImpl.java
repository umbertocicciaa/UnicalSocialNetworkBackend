package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.UserResponse;
import com.unicalsocial.backend.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapperInterface {

    public UserEntity toUserEntity(UserResponse user) {
        return UserEntity.builder()
                .id(user.getId())
                .profileName(user.getProfileName())
                .email(user.getEmail())
                .bio(user.getBio())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .profilePicture(user.getProfilePicture())
                .signupDate(user.getSignupDate())
                .build();
    }

    public UserResponse toUserResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .profileName(user.getProfileName())
                .email(user.getEmail())
                .bio(user.getBio())
                .firstName(user.getFirstname())
                .lastName(user.getLastname())
                .profilePicture(user.getProfilePicture())
                .signupDate(user.getSignupDate())
                .build();
    }
}
