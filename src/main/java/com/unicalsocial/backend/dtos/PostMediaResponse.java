package com.unicalsocial.backend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostMediaResponse {
    private Integer id;
    private Integer postId;
    private byte[] mediaFile;
}
