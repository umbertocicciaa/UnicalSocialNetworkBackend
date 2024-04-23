package com.unicalsocial.backend.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private int id;
    private int createdByUserid;
    private LocalDateTime createDatetime;
    private String caption;
    private int postType;
}
