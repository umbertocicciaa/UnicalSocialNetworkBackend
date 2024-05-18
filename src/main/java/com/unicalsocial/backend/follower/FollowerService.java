package com.unicalsocial.backend.follower;


import org.springframework.security.core.Authentication;

public interface FollowerService {
    FollowerNumberResponse countFollowers(int userId);
    FollowingNumberResponse countFollowing(int userId);
    FollowerCreatedResponse followUser(Authentication authentication , FollowerRequest userToFollow);
    IsFollowingResponse isFollowing(int user, int userToFollow);
}
