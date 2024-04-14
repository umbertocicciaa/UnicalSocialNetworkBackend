package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
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

    public int getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(int followingUserId) {
        this.followingUserId = followingUserId;
    }

    public int getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(int followerUserId) {
        this.followerUserId = followerUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowerEntity that = (FollowerEntity) o;
        return followingUserId == that.followingUserId && followerUserId == that.followerUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingUserId, followerUserId);
    }

    public UserEntity getUserByFollowingUserId() {
        return userByFollowingUserId;
    }

    public void setUserByFollowingUserId(UserEntity userByFollowingUserId) {
        this.userByFollowingUserId = userByFollowingUserId;
    }

    public UserEntity getUserByFollowerUserId() {
        return userByFollowerUserId;
    }

    public void setUserByFollowerUserId(UserEntity userByFollowerUserId) {
        this.userByFollowerUserId = userByFollowerUserId;
    }
}
