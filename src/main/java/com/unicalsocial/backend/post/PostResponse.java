package com.unicalsocial.backend.post;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PostResponse {
    private Integer id;
    private Integer userId;
    private String postType;
    private String caption;
    private Integer like;
}
