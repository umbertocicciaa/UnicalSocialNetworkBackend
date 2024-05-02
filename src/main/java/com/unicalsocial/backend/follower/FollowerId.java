package com.unicalsocial.backend.follower;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 9121542656082919003L;

    @NotNull
    @Column(name = "follower_user_id", nullable = false)
    private Integer followerUserId;

    @NotNull
    @Column(name = "following_user_id", nullable = false)
    private Integer followingUserId;

}