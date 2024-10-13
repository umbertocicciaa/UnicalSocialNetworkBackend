package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.CommentCreateRequest;
import com.unicalsocial.backend.dtos.CommentCreatedResponse;
import com.unicalsocial.backend.dtos.CommentDeletedResponse;
import com.unicalsocial.backend.dtos.CommentResponse;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface CommentService {
    CommentCreatedResponse createComment(CommentCreateRequest comment, Authentication authentication);
    Collection<CommentResponse> getCommentByPostId(int postId, int page);
    CommentDeletedResponse deleteCommentOfPost(int commentId, Authentication authentication);
}
