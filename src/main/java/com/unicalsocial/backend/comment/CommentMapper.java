package com.unicalsocial.backend.comment;

public interface CommentMapper {
    CommentCreatedResponse toCommentCreatedResponse(CommentEntity comment);
}
