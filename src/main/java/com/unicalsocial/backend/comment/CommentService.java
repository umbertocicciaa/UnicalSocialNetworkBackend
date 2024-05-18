package com.unicalsocial.backend.comment;

import org.springframework.security.core.Authentication;

public interface CommentService {
    CommentCreatedResponse createComment(CommentCreateRequest comment, Authentication authentication);
}
