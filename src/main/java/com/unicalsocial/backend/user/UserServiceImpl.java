package com.unicalsocial.backend.user;

import com.unicalsocial.backend.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
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
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        var user = this.userRepository.findByProfileName(username);
        return user.map(userMapper::toUserResponse).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserResponse> getUserLikeUsername(String username, int page) {
        final var pageSize = 25;
        final var offset = (page) * pageSize;
        var users = this.userRepository.findAllByProfileNameContainingOrderedByFollowerCount(username, pageSize, offset);
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserCountResponse countAllUsersLikeUsername(String username) {
        return UserCountResponse.builder()
                .count(this.userRepository.countUsersByProfileNameContaining(username))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getLoggedUser(Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        return this.userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UpdateUserRequest userUpdateRequest, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if(user==null)
            throw new UserNotFoundException();
        if(userUpdateRequest.getFirstName()!=null && !userUpdateRequest.getFirstName().isEmpty())
            user.setFirstname(userUpdateRequest.getFirstName());
        if(userUpdateRequest.getLastName()!=null && !userUpdateRequest.getLastName().isEmpty())
            user.setLastname(userUpdateRequest.getLastName());
        if(userUpdateRequest.getEmail()!=null && !userUpdateRequest.getEmail().isEmpty())
            user.setEmail(userUpdateRequest.getEmail());
        if(userUpdateRequest.getBio()!=null && !userUpdateRequest.getBio().isEmpty())
            user.setBio(userUpdateRequest.getBio());
        if(userUpdateRequest.getPhoto()!=null && userUpdateRequest.getPhoto().length>0)
            user.setProfilePicture(userUpdateRequest.getPhoto());
        var savedUser = this.userRepository.save(user);
        return this.userMapper.toUserResponse(savedUser);
    }
}
