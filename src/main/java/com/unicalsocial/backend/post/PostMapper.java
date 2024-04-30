package com.unicalsocial.backend.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    
    PostDTO postToDto(PostEntity post);

    PostEntity postDtoToPost(PostDTO postDTO);
}

