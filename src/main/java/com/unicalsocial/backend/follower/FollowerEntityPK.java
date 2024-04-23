package com.unicalsocial.backend.follower;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
@Data
public class FollowerEntityPK implements Serializable {
    @Column(name = "following_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followingUserId;
    @Column(name = "follower_user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followerUserId;

}
