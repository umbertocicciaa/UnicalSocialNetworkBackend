package com.unicalsocial.backend.follower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerDTO {
    private int followingUserId;
    private int followerUserId;
}