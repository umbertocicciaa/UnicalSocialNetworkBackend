package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private int createdByUserid;
    private LocalDateTime createDatetime;
    private String caption;
    private int postType;
}
