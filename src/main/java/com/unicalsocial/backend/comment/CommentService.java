package com.unicalsocial.backend.comment;

import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface CommentService {
    CommentCreatedResponse createComment(CommentCreateRequest comment, Authentication authentication);
    Collection<CommentResponse> getCommentByPostId(int postId,int page);
    CommentDeletedResponse deleteCommentOfPost(int commentId, Authentication authentication);
}
