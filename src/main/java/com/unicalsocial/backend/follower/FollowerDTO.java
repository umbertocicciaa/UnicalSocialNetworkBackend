package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerDTO {
    private UserDTO followerUserEntity;
    private UserDTO followingUserEntity;
}
