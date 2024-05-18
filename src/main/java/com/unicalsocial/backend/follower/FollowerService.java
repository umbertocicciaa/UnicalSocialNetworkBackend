package com.unicalsocial.backend.follower;


import org.springframework.security.core.Authentication;

public interface FollowerService {
    Long countFollowers(int userId);
    Long countFollowing(int userId);
    FollowerDTO followUser(Authentication authentication ,FollowerRequest userToFollow);
    Boolean isFollowing(int user, int userToFollow);
}
