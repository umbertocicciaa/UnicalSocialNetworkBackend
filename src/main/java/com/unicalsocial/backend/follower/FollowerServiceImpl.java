package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.exception.CantFollowSameUserException;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final FollowerMapper followerMapper;

    @Override
    @Transactional(readOnly = true)
    public FollowerNumberResponse countFollowers(int userId) {
        return FollowerNumberResponse.builder()
                .followerNumber(Math.toIntExact(this.followerRepository.countFollowersByUserId(userId)))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public FollowingNumberResponse countFollowing(int userId) {
        return FollowingNumberResponse.builder()
                .followingNumber(Math.toIntExact(this.followerRepository.countFollowingByUserId(userId)))
                .build();
    }

    @Override
    @Transactional
    public FollowerCreatedResponse followUser(Authentication authentication, FollowerRequest userToFollowId) {
        var user = (UserEntity) authentication.getPrincipal();
        if (Objects.equals(user.getId(), userToFollowId.getUserId()))
            throw new CantFollowSameUserException();
        var userToFollow = this.userRepository.findById(userToFollowId.getUserId()).orElseThrow(UserNotFoundException::new);
        var followerEntity = FollowerEntity.builder()
                .id(new FollowerId(user.getId(), userToFollow.getId()))
                .followerUserEntity(user)
                .followingUserEntity(userToFollow)
                .build();
        var follow = this.followerRepository.save(followerEntity);
        return this.followerMapper.toFollowerCreatedRespinse(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public IsFollowingResponse isFollowing(int user, int userToFollow) {
        var follwing = this.followerRepository.findById(new FollowerId(user, userToFollow));
        var isFollowing = follwing.isPresent();
        return IsFollowingResponse.builder()
                .isFollowing(isFollowing)
                .build();
    }
}
