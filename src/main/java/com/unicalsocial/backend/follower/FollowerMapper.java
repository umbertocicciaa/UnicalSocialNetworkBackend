package com.unicalsocial.backend.follower;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowerMapper {
    FollowerMapper INSTANCE = Mappers.getMapper(FollowerMapper.class);

    FollowerDTO followerToDto(FollowerEntity follower);

    FollowerEntity followerDtoToFollower(FollowerDTO followerDTO);
}
