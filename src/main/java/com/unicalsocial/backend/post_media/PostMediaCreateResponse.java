package com.unicalsocial.backend.post_media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostMediaCreateResponse {
    private Integer id;
    private Integer postId;
    private byte[] mediaFile;
}
