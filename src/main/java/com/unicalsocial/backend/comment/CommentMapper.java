package com.unicalsocial.backend.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO commentToCommentDto(CommentEntity comment);
    CommentEntity commenDtotToComment(CommentDTO comment);
}
