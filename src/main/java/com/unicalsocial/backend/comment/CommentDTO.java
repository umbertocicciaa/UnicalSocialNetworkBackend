package com.unicalsocial.backend.comment;

import com.unicalsocial.backend.post.PostDTO;
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
public class CommentDTO {
    private int id;
    private CommentDTO commentEntityRepliedTo;
    private UserDTO createdByUserid;
    private PostDTO postEntity;
    private Instant createdDatetime;
    private String comment;
}
