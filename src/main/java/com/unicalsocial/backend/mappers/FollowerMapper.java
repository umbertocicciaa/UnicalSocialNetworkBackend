package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.FollowerCreatedResponse;
import com.unicalsocial.backend.models.FollowerEntity;

public interface FollowerMapper {
    FollowerCreatedResponse toFollowerCreatedRespinse(FollowerEntity followerEntity);
}
