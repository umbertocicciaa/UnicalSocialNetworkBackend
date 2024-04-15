package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dto.UserDTO;
import com.unicalsocial.backend.dto.UserMapper;
import com.unicalsocial.backend.models.UserEntity;
import com.unicalsocial.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public UserDTO getUserById(int id) {
        return userRepository.findById(id)
                .map(UserMapper.ISTANCE::userToUSerDto)
                .orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = UserMapper.ISTANCE.userDtoToUser(userDTO);
        UserEntity userAdded = userRepository.save(user);
        return UserMapper.ISTANCE.userToUSerDto(userAdded);
    }

}
