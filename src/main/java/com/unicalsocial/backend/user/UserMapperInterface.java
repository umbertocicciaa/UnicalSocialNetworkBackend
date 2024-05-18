package com.unicalsocial.backend.user;

public interface UserMapperInterface {
     UserEntity toUserEntity(UserResponse user);
     UserResponse toUserResponse(UserEntity user);
}
