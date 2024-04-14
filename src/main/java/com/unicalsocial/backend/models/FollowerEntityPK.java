package com.unicalsocial.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class FollowerEntityPK implements Serializable {
    @Column(name = "following_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followingUserId;
    @Column(name = "follower_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followerUserId;

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
        FollowerEntityPK that = (FollowerEntityPK) o;
        return followingUserId == that.followingUserId && followerUserId == that.followerUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followingUserId, followerUserId);
    }
}
