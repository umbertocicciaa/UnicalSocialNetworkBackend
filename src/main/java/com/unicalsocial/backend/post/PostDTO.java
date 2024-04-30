package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserDTO;
import com.unicalsocial.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Integer id;
    private UserDTO createdByUserid;
    private PostTypeDTO postTypeEntity;
    private Instant createDatetime;
    private String caption;
    private Integer like;
}
