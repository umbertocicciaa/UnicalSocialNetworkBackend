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
public class CommentResponse {
    private Integer id;
    private CommentUserResponse createdByUserid;
    private Integer postId;
    private Instant createdDatetime;
    private String comment;
}
