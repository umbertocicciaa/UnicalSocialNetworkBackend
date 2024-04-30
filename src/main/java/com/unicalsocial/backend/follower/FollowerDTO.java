package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerDTO {
    private UserEntity followerUserEntity;
    private UserEntity followingUserEntity;
}
