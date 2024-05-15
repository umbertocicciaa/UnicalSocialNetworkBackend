package com.unicalsocial.backend.post_type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostTypeDTO {
    private Integer id;
    private String postTypeName;
}
