package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper ISTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "createdByUserid", target = "createdByUserid")
    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "createdDatetime", target = "createdDatetime")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "commentRepliedToId", target = "commentRepliedToId")
    CommentDTO commentToCommentDto(CommentEntity comment);

    @Mapping(source = "createdByUserid", target = "createdByUserid")
    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "createdDatetime", target = "createdDatetime")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "commentRepliedToId", target = "commentRepliedToId")
    CommentEntity commenDtotToComment(CommentDTO comment);

}
