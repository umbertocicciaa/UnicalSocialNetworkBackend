package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int createdByUserid;
    private int postId;
    private Object createdDatetime;
    private String comment;
    private Integer commentRepliedToId;
}
