package com.unicalsocial.backend.mipiace;

import com.unicalsocial.backend.post.PostEntity;
import com.unicalsocial.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MipiaceDTO {
    private Integer postId;
    private Integer userId;
}
