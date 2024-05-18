package com.unicalsocial.backend.comment;

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
    private Integer createdByUserid;
    private Integer postId;
    private Instant createdDate;
    private String comment;
}
