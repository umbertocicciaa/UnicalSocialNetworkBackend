package com.unicalsocial.backend.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentUserResponse {
    private Integer userId;
    private String username;
    private byte[] avatar;
}
