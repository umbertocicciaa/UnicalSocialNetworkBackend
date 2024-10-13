package com.unicalsocial.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreatedResponse {
    private byte[] pics;
    private Integer id;
    private Integer userId;
    private String caption;
    private String postType;
    private Integer like;
}
