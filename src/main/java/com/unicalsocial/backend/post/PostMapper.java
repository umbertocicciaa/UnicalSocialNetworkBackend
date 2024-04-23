package com.unicalsocial.backend.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper ISTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "createdByUserid", target = "createdByUserid")
    @Mapping(source = "createDatetime", target = "createDatetime")
    @Mapping(source = "caption", target = "caption")
    @Mapping(source = "postType", target = "postType")
    PostDTO postToDto(PostEntity post);

    @Mapping(source = "createdByUserid", target = "createdByUserid")
    @Mapping(source = "createDatetime", target = "createDatetime")
    @Mapping(source = "caption", target = "caption")
    @Mapping(source = "postType", target = "postType")
    PostEntity postDtoToPost(PostDTO postDTO);
}
