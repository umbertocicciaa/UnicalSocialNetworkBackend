package com.unicalsocial.backend.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Integer id;
    private UserPostResponse user;
    private String postType;
    private String caption;
    private Integer like;
    private byte[] image;
}
