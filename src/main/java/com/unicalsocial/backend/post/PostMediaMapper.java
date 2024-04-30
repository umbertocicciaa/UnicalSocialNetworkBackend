package com.unicalsocial.backend.post;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaMapper {

    PostMediaMapper INSTANCE = Mappers.getMapper(PostMediaMapper.class);

    PostMediaDTO postMediaToPostMediaDTO(PostMediaEntity postMedia);

    PostMediaEntity postMediaDTOToPostMedia(PostMediaDTO postMediaDTO);
}
