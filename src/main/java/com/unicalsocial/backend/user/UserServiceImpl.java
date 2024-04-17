package com.unicalsocial.backend.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        userEntities.forEach(userEntity -> userDTOS.add(UserMapper.ISTANCE.userToUserDto(userEntity)));
        return userDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(int id) {
        return userRepository.findById(id)
                .map(UserMapper.ISTANCE::userToUserDto)
                .orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = UserMapper.ISTANCE.userDtoToUser(userDTO);
        UserEntity userAdded = userRepository.save(user);
        return UserMapper.ISTANCE.userToUserDto(userAdded);
    }

}
