package com.unicalsocial.backend.post_media;

import com.unicalsocial.backend.post.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMediaCreateRequest {
    private PostEntity postEntity;
    private byte[] mediaFile;
}
