package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {
    @Test
    public void shouldMapUserDto() {
        //given
        UserEntity user = new UserEntity();
        user.setFirstName("umberto domenico");
        user.setLastName("ciccia");
        user.setSignupDate(Calendar.getInstance());
        user.setProfileName("umbertocicciaa");
        //when
        UserDTO userDTO = UserMapper.ISTANCE.userToUSerDto(user);
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
        UserDTO userDTO = new UserDTO(1,"umberto domenico", "ciccia", "umbertocicciaa", Calendar.getInstance());
        //when
        UserEntity user = UserMapper.ISTANCE.userDtoToUser(userDTO);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDTO.getLastName());
        assertThat(user.getSignupDate()).isEqualTo(userDTO.getSignupDate());
        assertThat(user.getProfileName()).isEqualTo(userDTO.getProfileName());
    }

}
