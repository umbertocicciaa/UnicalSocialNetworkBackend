package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.exception.CantFollowSameUserException;
import com.unicalsocial.backend.exception.FollowerNotFoundException;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserMapperInterface;
import com.unicalsocial.backend.user.UserService;
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
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserService userService;
    private final UserMapperInterface userMapper;

    @Override
    @Transactional(readOnly = true)
    public Long countFollowers(int userId) {
        return this.followerRepository.countFollowersByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countFollowing(int userId) {
        return  this.followerRepository.countFollowingByUserId(userId);
    }

    @Override
    @Transactional
    public FollowerDTO followUser(Authentication authentication, FollowerRequest userToFollowId) {
        var user = (UserEntity) authentication.getPrincipal();
        if(Objects.equals(user.getId(), userToFollowId.getUserId()))
            throw new CantFollowSameUserException();
        var userToFollow = this.userService.getUserById(userToFollowId.getUserId());
        if(userToFollow == null)
            throw new UserNotFoundException();
        var followerEntity = FollowerEntity.builder()
                .id(new FollowerId(user.getId(),userToFollowId.getUserId()))
                .followerUserEntity(user)
                .followingUserEntity(this.userMapper.toUserEntity(userToFollow))
                .build();
        var follow = this.followerRepository.save(followerEntity);
        return FollowerMapper.INSTANCE.followerToDto(follow);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isFollowing(int user, int userToFollow) {
        this.followerRepository.findById(new FollowerId(user,userToFollow)).orElseThrow(FollowerNotFoundException::new);
        return true;
    }
}
