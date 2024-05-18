package com.unicalsocial.backend.post;

import com.unicalsocial.backend.post_type.PostTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreatedResponse {
    private Integer id;
    private PostTypeEntity postTypeEntity;
    private String caption;
    private Integer like;
}
