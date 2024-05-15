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

    @Transactional(readOnly = true)
    public Collection<UserDTO> getAllUser() {
        return this.userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDto).toList();
    }

    @Transactional(readOnly = true)
    public Collection<UserDTO> getAllUserOrderedBySignUpDate() {
        return this.userRepository.findAllByOrderBySignupDateAsc().stream().map(UserMapper.INSTANCE::userToUserDto).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(long id) {
        var userId = Math.toIntExact(id);
        var user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return  UserMapper.INSTANCE.userToUserDto(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        var user = this.userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDTO));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
       var user = this.userRepository.findByProfileName(username);
        return user.map(UserMapper.INSTANCE::userToUserDto).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserDTO> getUserLikeUsername(String username,int page) {
        final var  pageSize = 10;
        final var offset = (page) * pageSize;
        var users = this.userRepository.findAllByProfileNameContainingOrderedByFollowerCount(username, pageSize, offset);
        return users.stream().map(UserMapper.INSTANCE::userToUserDto).toList();
    }

    @Override
    public Long countAllUsersLikeUsername(String username) {
        return this.userRepository.countUsersByProfileNameContaining(username);
    }
}
