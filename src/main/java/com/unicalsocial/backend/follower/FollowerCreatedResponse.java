package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerCreatedResponse {
    private Integer followerUserEntity;
    private Integer followingUserEntity;
}
