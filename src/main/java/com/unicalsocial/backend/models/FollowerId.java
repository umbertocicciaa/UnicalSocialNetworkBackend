package com.unicalsocial.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


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