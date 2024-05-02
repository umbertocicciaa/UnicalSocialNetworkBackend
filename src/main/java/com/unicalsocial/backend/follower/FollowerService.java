package com.unicalsocial.backend.follower;

import org.springframework.http.ResponseEntity;

public interface FollowerService {
    ResponseEntity<Long> countFollowers(int userId);
    ResponseEntity<Long> countFollowing(int userId);
}
