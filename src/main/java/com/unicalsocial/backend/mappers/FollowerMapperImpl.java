package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.FollowerCreatedResponse;
import com.unicalsocial.backend.models.FollowerEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowerMapperImpl implements FollowerMapper {
    @Override
    public FollowerCreatedResponse toFollowerCreatedRespinse(FollowerEntity followerEntity) {
        return FollowerCreatedResponse.builder()
                .followerUserEntity(followerEntity.getFollowerUserEntity().getId())
                .followingUserEntity(followerEntity.getFollowingUserEntity().getId())
                .build();
    }
}
