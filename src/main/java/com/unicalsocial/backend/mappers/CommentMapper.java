package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.CommentCreatedResponse;
import com.unicalsocial.backend.dtos.CommentResponse;
import com.unicalsocial.backend.models.CommentEntity;

public interface CommentMapper {
    CommentCreatedResponse toCommentCreatedResponse(CommentEntity comment);
    CommentResponse toCommentResponse(CommentEntity comment);
}
