package com.unicalsocial.backend.follower;


import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public interface FollowerService {
    FollowerNumberResponse countFollowers(int userId);
    FollowingNumberResponse countFollowing(int userId);
    FollowerCreatedResponse followUser(Authentication authentication , FollowerRequest userToFollow);
    IsFollowingResponse isFollowing(int user, int userToFollow);
    Collection<UserResponse> getFollowingUsers(UserEntity user, int page);
}
