package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.*;
import com.unicalsocial.backend.exception.CantFollowSameUserException;
import com.unicalsocial.backend.exception.CantFollowTwoTimeSameUser;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.mappers.FollowerMapper;
import com.unicalsocial.backend.mappers.UserMapperInterface;
import com.unicalsocial.backend.models.FollowerEntity;
import com.unicalsocial.backend.models.FollowerId;
import com.unicalsocial.backend.models.UserEntity;
import com.unicalsocial.backend.repositories.FollowerRepository;
import com.unicalsocial.backend.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final FollowerMapper followerMapper;
    private final UserMapperInterface userMapper;

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
        var follower = this.followerRepository.findById(
                FollowerId.builder()
                        .followerUserId(user.getId())
                        .followingUserId(userToFollowId.getUserId())
                        .build()
        );
        if(follower.isPresent()) throw new CantFollowTwoTimeSameUser();
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

    @Override
    @Transactional(readOnly = true)
    public Collection<UserResponse> getFollowingUsers(UserEntity user, int page) {
        final var pageSize = 15;
        final var pageable = PageRequest.of(page, pageSize);
        var following = this.followerRepository.findFollowerEntitiesByFollowerUserEntity(user, pageable);
        var res = new ArrayList<UserEntity>();
        for(var follower : following)
            res.add(follower.getFollowingUserEntity());
        return res.stream().map(this.userMapper::toUserResponse).collect(Collectors.toList());
    }
}
