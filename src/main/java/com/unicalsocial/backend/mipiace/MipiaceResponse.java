package com.unicalsocial.backend.mipiace;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MipiaceResponse {
    private Integer postId;
    private Integer userId;
}
