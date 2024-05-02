package com.unicalsocial.backend.follower;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Long> countFollowers(int userId) {
        var follower = this.followerRepository.countFollowersByUserId(userId);
        return ResponseEntity.ok().body(follower);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Long> countFollowing(int userId) {
        var following = this.followerRepository.countFollowingByUserId(userId);
        return ResponseEntity.ok().body(following);
    }
}
