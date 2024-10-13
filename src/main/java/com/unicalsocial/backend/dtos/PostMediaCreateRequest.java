package com.unicalsocial.backend.dtos;

import com.unicalsocial.backend.models.PostEntity;
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
