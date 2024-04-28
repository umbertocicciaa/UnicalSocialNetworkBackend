package com.unicalsocial.backend.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void userRepository_getAllUser() {
        var userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .email("pippo@gmail.com")
                .build();

        var userEntity2 = UserEntity.builder()
                .firstName("pluto")
                .lastName("tromba")
                .profileName("pluto")
                .email("pluto@gmail.com")
                .build();

        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        var users = userRepository.findAll();

        Assertions.assertEquals(userEntity1, users.getFirst());
        Assertions.assertEquals(userEntity2, users.getLast());

    }

    @Test
    public void userRepository_getUserById() {
        var userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .email("pippo@gmail.com")
                .build();
        userRepository.save(userEntity1);
        Assertions.assertTrue(userRepository.existsById(userEntity1.getId()));
    }

    @Test
    public void userRepository_createUser() {
        var userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .email("pippo@gmail.com")
                .build();
        var result = userRepository.save(userEntity1);
        Assertions.assertEquals(userEntity1, result);
    }
}
