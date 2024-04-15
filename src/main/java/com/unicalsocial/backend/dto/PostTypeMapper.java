package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.PostTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostTypeMapper {
    PostTypeMapper ISTANCE = Mappers.getMapper(PostTypeMapper.class);

    @Mapping(source = "postTypeName", target = "postTypeName")
    PostTypeDTO postTypeToDto(PostTypeEntity post);

    @Mapping(source = "postTypeName", target = "postTypeName")
    PostTypeEntity postTypeDtoToPostType(PostTypeDTO post);

}
