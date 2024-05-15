package com.unicalsocial.backend.post_media;

import com.unicalsocial.backend.post.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMediaDTO {
    private Integer id;
    private PostDTO postEntity;
    private byte[] mediaFile;
}
