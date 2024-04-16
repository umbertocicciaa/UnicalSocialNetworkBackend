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
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setFirstName("pippo");
        userEntity1.setLastName("pippa");
        userEntity1.setProfileName("pippo");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setFirstName("pluto");
        userEntity2.setLastName("tromba");
        userEntity2.setProfileName("pluto");

        userRepository.save(userEntity1);
        userRepository.save(userEntity2);

        List<UserEntity> users = userRepository.findAll();

        Assertions.assertEquals(userEntity1, users.getFirst());
        Assertions.assertEquals(userEntity2, users.getLast());

    }

    @Test
    public void userRepository_getUserById() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setFirstName("pippo");
        userEntity1.setLastName("pippa");
        userEntity1.setProfileName("pippo");
        userRepository.save(userEntity1);
        Assertions.assertTrue(userRepository.existsById(userEntity1.getId()));
    }

    @Test
    public void userRepository_createUser() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setFirstName("pippo");
        userEntity1.setLastName("pippa");
        userEntity1.setProfileName("pippo");
        UserEntity result = userRepository.save(userEntity1);
        Assertions.assertEquals(userEntity1, result);
    }
}
