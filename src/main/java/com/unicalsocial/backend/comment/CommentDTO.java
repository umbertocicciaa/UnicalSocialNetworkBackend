package com.unicalsocial.backend.comment;

import com.unicalsocial.backend.post.PostResponse;
import com.unicalsocial.backend.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private int id;
    private CommentDTO commentEntityRepliedTo;
    private UserResponse createdByUserid;
    private PostResponse postEntity;
    private Instant createdDatetime;
    private String comment;
}
