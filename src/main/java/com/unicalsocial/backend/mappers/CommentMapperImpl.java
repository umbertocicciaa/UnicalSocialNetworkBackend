package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.CommentCreatedResponse;
import com.unicalsocial.backend.dtos.CommentResponse;
import com.unicalsocial.backend.dtos.CommentUserResponse;
import com.unicalsocial.backend.models.CommentEntity;
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
                .createdByUserid(
                        CommentUserResponse.builder()
                                .username(comment.getCreatedByUserid().getUsername())
                                .avatar(comment.getCreatedByUserid().getProfilePicture())
                                .userId(comment.getCreatedByUserid().getId())
                                .build()
                )
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
