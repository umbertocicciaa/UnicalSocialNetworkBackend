package com.unicalsocial.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreatedResponse {
    private Integer id;
    private Integer commentRepliedId;
    private CommentUserResponse createdByUserid;
    private Integer postId;
    private Instant createdDate;
    private String comment;
}
