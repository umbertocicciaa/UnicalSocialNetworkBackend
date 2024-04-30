package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "follower")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    @EmbeddedId
    private FollowerId id;

    @MapsId("followerUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("nextval('follower_follower_user_id_seq'")
    @JoinColumn(name = "follower_user_id", nullable = false)
    private User followerUser;

    @MapsId("followingUserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_user_id", nullable = false)
    private User followingUser;

}