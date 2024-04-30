package com.unicalsocial.backend.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO user1, user2;

    @BeforeEach
    public void setUp() {
        var userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .build();

        var userEntity2 = UserEntity.builder()
                .firstName("pluto")
                .lastName("pippa")
                .profileName("pluto")
                .build();
        this.user1 = UserMapper.INSTANCE.userToUserDto(userEntity1);
        this.user2 = UserMapper.INSTANCE.userToUserDto(userEntity2);
    }

    @Test
    public void getAllUser() {
        when(userRepository.findAll()).thenReturn(List.of(UserMapper.INSTANCE.userDtoToUser(user1), UserMapper.INSTANCE.userDtoToUser(user2)));
        var users = userService.getAllUser();
        Assertions.assertEquals(2, Objects.requireNonNull(users.getBody()).size());
        Assertions.assertEquals(user1.getProfileName(), users.getBody().getFirst().getProfileName());
        Assertions.assertEquals(user2.getProfileName(), users.getBody().getLast().getProfileName());
    }

    @Test
    public void getUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(UserMapper.INSTANCE.userDtoToUser(user1)));
        var user = userService.getUserById(1);
        Assertions.assertEquals(user1.getProfileName(), Objects.requireNonNull(user.getBody()).getProfileName());
    }

    @Test
    public void createUser() {
        when(userRepository.save(UserMapper.INSTANCE.userDtoToUser(user1))).thenReturn(UserMapper.INSTANCE.userDtoToUser(user1));
        var user = userService.createUser(user1);
        Assertions.assertEquals(user1.getProfileName(), Objects.requireNonNull(user.getBody()).getProfileName());
    }

}
