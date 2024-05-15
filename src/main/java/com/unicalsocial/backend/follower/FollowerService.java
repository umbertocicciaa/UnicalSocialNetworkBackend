package com.unicalsocial.backend.follower;


public interface FollowerService {
    Long countFollowers(int userId);
    Long countFollowing(int userId);
    FollowerDTO followUser(int userId, int userToFollowId);
    Boolean isFollowing(int user, int userToFollow);
}
