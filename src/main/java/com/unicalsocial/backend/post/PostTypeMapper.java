package com.unicalsocial.backend.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostTypeMapper {
    PostTypeMapper INSTANCE = Mappers.getMapper(PostTypeMapper.class);

    PostTypeDTO postTypeToDto(PostTypeEntity post);

    PostTypeEntity postTypeDtoToPostType(PostTypeDTO post);

}
