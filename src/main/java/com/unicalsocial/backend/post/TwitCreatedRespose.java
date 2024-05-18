package com.unicalsocial.backend.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TwitCreatedRespose {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String caption;
    private Integer like;
}
