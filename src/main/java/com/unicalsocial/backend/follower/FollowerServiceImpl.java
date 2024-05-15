package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.exception.CantFollowSameUserException;
import com.unicalsocial.backend.exception.FollowerNotFoundException;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.user.UserMapper;
import com.unicalsocial.backend.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserService userService;

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
    public FollowerDTO followUser(int userId, int userToFollowId) {
        if(userId==userToFollowId)
            throw new CantFollowSameUserException();
        var user = this.userService.getUserById(userId);
        var userToFollow = this.userService.getUserById(userToFollowId);
        if(user==null || userToFollow==null)
            throw new UserNotFoundException();
        var followerEntity = FollowerEntity.builder()
                .id(new FollowerId(userId,userToFollowId))
                .followerUserEntity(UserMapper.INSTANCE.userDtoToUser(user))
                .followingUserEntity(UserMapper.INSTANCE.userDtoToUser(userToFollow))
                .build();
        var follow = this.followerRepository.save(followerEntity);
        return FollowerMapper.INSTANCE.followerToDto(follow);
    }

    @Override
    public Boolean isFollowing(int user, int userToFollow) {
        this.followerRepository.findById(new FollowerId(user,userToFollow)).orElseThrow(FollowerNotFoundException::new);
        return true;
    }
}
