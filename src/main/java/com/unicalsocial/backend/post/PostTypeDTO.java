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
    private int id;
    private String postTypeName;
}
