package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "follower")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowerEntity {
    @EmbeddedId
    private FollowerId id;

    @MapsId("followerUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_user_id", nullable = false)
    private UserEntity followerUserEntity;

    @MapsId("followingUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_user_id", nullable = false)
    private UserEntity followingUserEntity;

}