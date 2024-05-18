package com.unicalsocial.backend.user;

import com.unicalsocial.backend.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapperInterface userMapper;

    @Transactional(readOnly = true)
    public Collection<UserResponse> getAllUser() {
        return this.userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Transactional(readOnly = true)
    public Collection<UserResponse> getAllUserOrderedBySignUpDate() {
        return this.userRepository.findAllByOrderBySignupDateAsc().stream().map(userMapper::toUserResponse).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(long id) {
        var userId = Math.toIntExact(id);
        var user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return  userMapper.toUserResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
       var user = this.userRepository.findByProfileName(username);
        return user.map(userMapper::toUserResponse).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserResponse> getUserLikeUsername(String username,int page) {
        final var  pageSize = 10;
        final var offset = (page) * pageSize;
        var users = this.userRepository.findAllByProfileNameContainingOrderedByFollowerCount(username, pageSize, offset);
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public Long countAllUsersLikeUsername(String username) {
        return this.userRepository.countUsersByProfileNameContaining(username);
    }
}
