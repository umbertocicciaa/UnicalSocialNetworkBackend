package com.unicalsocial.backend.post_media;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostMediaResponse {
    private Integer id;
    private Integer postId;
    private byte[] mediaFile;
}
