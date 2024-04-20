package com.unicalsocial.backend.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {
    @Test
    public void shouldMapUserDto() {
        //given
        UserEntity user = new UserEntity();
        user.setFirstName("umberto domenico");
        user.setLastName("ciccia");
        user.setSignupDate(LocalDateTime.now());
        user.setProfileName("umbertocicciaa");
        //when
        UserDTO userDTO = UserMapper.ISTANCE.userToUserDto(user);
        //then
        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDTO.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDTO.getSignupDate()).isEqualTo(user.getSignupDate());
        assertThat(userDTO.getProfileName()).isEqualTo(user.getProfileName());
    }

    @Test
    public void shouldMapUser() {
        //given
        UserDTO userDTO = UserDTO.builder().firstName("s").lastName("a").build();
        //when
        UserEntity user = UserMapper.ISTANCE.userDtoToUser(userDTO);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDTO.getLastName());
    }

}
