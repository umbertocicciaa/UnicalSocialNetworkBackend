package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int createdByUserid;
    private Object createDatetime;
    private String caption;
    private int postType;
}
