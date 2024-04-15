package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dto.UserDTO;
import com.unicalsocial.backend.dto.UserMapper;
import com.unicalsocial.backend.models.UserEntity;
import com.unicalsocial.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        userEntities.forEach(userEntity -> userDTOS.add(UserMapper.ISTANCE.userToUSerDto(userEntity)));
        return userDTOS;
    }


}
