package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.UserResponse;
import com.unicalsocial.backend.models.UserEntity;

public interface UserMapperInterface {
     UserEntity toUserEntity(UserResponse user);
     UserResponse toUserResponse(UserEntity user);
}
