package com.unicalsocial.backend.follower;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowerMapper {
    FollowerMapper ISTANCE = Mappers.getMapper(FollowerMapper.class);

    @Mapping(source = "followingUserId", target = "followingUserId")
    @Mapping(source = "followerUserId", target = "followerUserId")
    FollowerDTO followerToDto(FollowerEntity follower);

    @Mapping(source = "followingUserId", target = "followingUserId")
    @Mapping(source = "followerUserId", target = "followerUserId")
    FollowerEntity followerDtoToFollower(FollowerDTO followerDTO);
}
