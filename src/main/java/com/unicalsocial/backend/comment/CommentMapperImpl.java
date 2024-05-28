package com.unicalsocial.backend.comment;

import org.springframework.stereotype.Service;

@Service
public class CommentMapperImpl implements CommentMapper {


    @Override
    public CommentCreatedResponse toCommentCreatedResponse(CommentEntity comment) {
        return CommentCreatedResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .postId(comment.getPostEntity().getId())
                .createdDate(comment.getCreatedDatetime())
                .createdByUserid(comment.getCreatedByUserid().getId())
                .build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentEntity comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .createdDatetime(comment.getCreatedDatetime())
                .createdByUserid(CommentUserResponse.builder()
                        .userId(comment.getCreatedByUserid().getId())
                        .avatar(comment.getCreatedByUserid().getProfilePicture())
                        .username(comment.getCreatedByUserid().getProfileName())
                        .build())
                .postId(comment.getPostEntity().getId())
                .build();
    }
}
