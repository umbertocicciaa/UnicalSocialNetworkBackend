package com.unicalsocial.backend.post;

import com.unicalsocial.backend.post_type.PostTypeDTO;
import com.unicalsocial.backend.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
