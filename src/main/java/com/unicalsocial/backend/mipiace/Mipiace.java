package com.unicalsocial.backend.mipiace;

import com.unicalsocial.backend.post.PostEntity;
import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "mipiace")
public class Mipiace {
    @EmbeddedId
    private MipiaceId id;

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}