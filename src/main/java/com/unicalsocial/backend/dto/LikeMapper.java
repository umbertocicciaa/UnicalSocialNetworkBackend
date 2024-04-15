package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.LikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LikeMapper {
    LikeMapper ISTANCE = Mappers.getMapper(LikeMapper.class);

    LikeDTO likeToDto(LikeEntity like);

    LikeEntity likeDtoToLike(LikeDTO likeDTO);
}
