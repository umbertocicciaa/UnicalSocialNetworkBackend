package com.unicalsocial.backend.post;

import com.unicalsocial.backend.post_type.PostTypeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreatedResponse {
    private Integer id;
    private PostTypeEntity postTypeEntity;
    private String caption;
    private Integer like;
}
