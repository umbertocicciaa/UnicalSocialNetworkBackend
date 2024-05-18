package com.unicalsocial.backend.post;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String caption;
    private byte[] mediaFile;
}
