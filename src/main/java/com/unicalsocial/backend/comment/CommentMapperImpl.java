package com.unicalsocial.backend.comment;

import org.springframework.stereotype.Service;

@Service
public class CommentMapperImpl implements CommentMapper{


    @Override
    public CommentCreatedResponse toCommentCreatedResponse(CommentEntity comment) {
        return CommentCreatedResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .postId(comment.getPostEntity().getId())
                .createdDate(comment.getCreatedDatetime())
                .createdByUserid(comment.getCreatedByUserid().getId())
                .commentRepliedId(comment.getCommentEntityRepliedTo().getId())
                .build();
    }
}
