package com.unicalsocial.backend.post_media;

import com.unicalsocial.backend.post.PostEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostMediaCreateRequest {
    private PostEntity postEntity;
    private byte[] mediaFile;
}
