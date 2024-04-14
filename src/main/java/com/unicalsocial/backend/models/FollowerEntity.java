package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follower", schema = "public", catalog = "unical_social_network")
@IdClass(FollowerEntityPK.class)
public class FollowerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "following_user_id")
    private int followingUserId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "follower_user_id")
    private int followerUserId;
    @ManyToOne
    @JoinColumn(name = "following_user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByFollowingUserId;
    @ManyToOne
    @JoinColumn(name = "follower_user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByFollowerUserId;
}
