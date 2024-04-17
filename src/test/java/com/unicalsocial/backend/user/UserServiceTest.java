package com.unicalsocial.backend.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDTO user1, user2;

    @BeforeEach
    public void setUp() {
        UserEntity userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .build();

        UserEntity userEntity2 = UserEntity.builder()
                .firstName("pluto")
                .lastName("pippa")
                .profileName("pluto")
                .build();
        this.user1 = UserMapper.ISTANCE.userToUserDto(userEntity1);
        this.user2 = UserMapper.ISTANCE.userToUserDto(userEntity2);
    }

    @Test
    public void getAllUser() {
        when(userRepository.findAll()).thenReturn(List.of(UserMapper.ISTANCE.userDtoToUser(user1), UserMapper.ISTANCE.userDtoToUser(user2)));

        List<UserDTO> users = userService.getAllUser();
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(user1.getProfileName(), users.get(0).getProfileName());
        Assertions.assertEquals(user2.getProfileName(), users.get(1).getProfileName());
    }

    @Test
    public void getUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(UserMapper.ISTANCE.userDtoToUser(user1)));

        UserDTO user = userService.getUserById(1);
        Assertions.assertEquals(user1.getProfileName(), user.getProfileName());
    }

    @Test
    public void createUser() {
        when(userRepository.save(UserMapper.ISTANCE.userDtoToUser(user1))).thenReturn(UserMapper.ISTANCE.userDtoToUser(user1));
        UserDTO user = userService.createUser(user1);
        Assertions.assertEquals(user1.getProfileName(), user.getProfileName());
    }

}
