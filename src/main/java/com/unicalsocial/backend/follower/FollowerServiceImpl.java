package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserMapper;
import com.unicalsocial.backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserService userService;

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

    @Override
    public ResponseEntity<FollowerDTO> followUser(int userId, int userToFollowId) {
        if(userId==userToFollowId)
            return ResponseEntity.badRequest().build();
        var user = this.userService.getUserById(userId);
        var userToFollow = this.userService.getUserById(userToFollowId);
        if(user.getBody()==null || userToFollow.getBody()==null)
            return ResponseEntity.badRequest().build();
        var followerEntity = FollowerEntity.builder()
                .id(new FollowerId(userId,userToFollowId))
                .followerUserEntity(UserMapper.INSTANCE.userDtoToUser(user.getBody()))
                .followingUserEntity(UserMapper.INSTANCE.userDtoToUser(userToFollow.getBody()))
                .build();
        var follow = this.followerRepository.save(followerEntity);
        return ResponseEntity.ok(FollowerMapper.INSTANCE.followerToDto(follow));
    }

    @Override
    public ResponseEntity<Boolean> isFollowing(int user, int userToFollow) {
        var follow = this.followerRepository.findById(new FollowerId(user,userToFollow));
        if(follow.isEmpty())
            return ResponseEntity.ok(false);
        return ResponseEntity.ok(true);
    }
}
