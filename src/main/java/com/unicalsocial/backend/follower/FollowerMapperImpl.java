package com.unicalsocial.backend.follower;

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
