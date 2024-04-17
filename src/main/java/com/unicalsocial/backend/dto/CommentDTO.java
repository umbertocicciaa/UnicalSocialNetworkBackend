package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private int id;
    private int createdByUserid;
    private int postId;
    private LocalDateTime createdDatetime;
    private String comment;
    private Integer commentRepliedToId;
}
