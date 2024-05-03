package com.unicalsocial.backend.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Collection<UserDTO>> getAllUser() {
        var users = this.userRepository.findAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(users.stream().map(UserMapper.INSTANCE::userToUserDto).toList());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Collection<UserDTO>> getAllUserOrderedBySignUpDate() {
        var users = this.userRepository.findAllByOrderBySignupDateAsc();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(users.stream().map(UserMapper.INSTANCE::userToUserDto).toList());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserDTO> getUserById(long id) {
        var user = this.userRepository.findById((int) id).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserDto(user));
    }

    @Transactional
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        try {
            var user = this.userRepository.save(UserMapper.INSTANCE.userDtoToUser(userDTO));
            return new ResponseEntity<>(UserMapper.INSTANCE.userToUserDto(user), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserDTO> getUserByUsername(String username) {
       var user = this.userRepository.findByProfileName(username);
       if(user == null)
           return ResponseEntity.notFound().build();
       return ResponseEntity.ok().body(UserMapper.INSTANCE.userToUserDto(user));
    }
}
