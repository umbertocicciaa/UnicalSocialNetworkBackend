package com.unicalsocial.backend.post_media;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaMapper {
    PostMediaMapper ISTANCE = Mappers.getMapper(PostMediaMapper.class);

    PostMediaDTO postMediaToPostMediaDTO(PostMedia postMedia);

    PostMedia postMediaDTOToPostMedia(PostMediaDTO postMediaDTO);
}
