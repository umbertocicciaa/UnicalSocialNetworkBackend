package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.UpdateUserRequest;
import com.unicalsocial.backend.dtos.UserCountResponse;
import com.unicalsocial.backend.dtos.UserResponse;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.mappers.UserMapperInterface;
import com.unicalsocial.backend.models.UserEntity;
import com.unicalsocial.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapperInterface userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        try (var ignored = MockitoAnnotations.openMocks(this)) {
        }
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(userService.getAllUser().isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
        when(userMapper.toUserResponse(userEntity)).thenReturn(new UserResponse());

        UserResponse userResponse = userService.getUserById(1L);
        assertNotNull(userResponse);
        verify(userRepository, times(1)).findById(1);
        verify(userMapper, times(1)).toUserResponse(userEntity);
    }

    @Test
    void getUserByUsername() {
        UserEntity userEntity = new UserEntity();
        when(userRepository.findByProfileName("username")).thenReturn(Optional.of(userEntity));
        when(userMapper.toUserResponse(userEntity)).thenReturn(new UserResponse());

        UserResponse userResponse = userService.getUserByUsername("username");
        assertNotNull(userResponse);
        verify(userRepository, times(1)).findByProfileName("username");
        verify(userMapper, times(1)).toUserResponse(userEntity);
    }

    @Test
    void updateUser() {
        UserEntity userEntity = new UserEntity();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.toUserResponse(userEntity)).thenReturn(new UserResponse());

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setFirstName("NewFirstName");

        UserResponse userResponse = userService.updateUser(updateUserRequest, authentication);
        assertNotNull(userResponse);
        verify(userRepository, times(1)).save(userEntity);
        verify(userMapper, times(1)).toUserResponse(userEntity);
    }
}