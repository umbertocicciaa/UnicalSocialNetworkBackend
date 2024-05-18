package com.unicalsocial.backend.mipiace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedMipiceResponse {

    private Integer postId;
    private Integer userId;
}
