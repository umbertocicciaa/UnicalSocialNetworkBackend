package com.unicalsocial.backend.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = UserRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private UserDTO user1, user2;


    @BeforeEach
    void setUp() {
        var userEntity1 = UserEntity.builder()
                .firstName("pippo")
                .lastName("pippa")
                .profileName("pippo")
                .build();

        var userEntity2 = UserEntity.builder()
                .firstName("pluto")
                .lastName("tromba")
                .profileName("pluto")
                .build();
        this.user1 = UserMapper.INSTANCE.userToUserDto(userEntity1);
        this.user2 = UserMapper.INSTANCE.userToUserDto(userEntity2);
    }

    @Test
    public void getAllUsers() throws Exception {
        when(userService.getAllUser()).thenReturn(ResponseEntity.ok().body(Arrays.asList(user1, user2)));
        ResultActions response = mockMvc.perform(get("/api/v1/User"+"/users").contentType(MediaType.APPLICATION_JSON));
        response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(user1, user2).size())));
    }

    @Test
    public void getUserById() throws Exception {
        // Mocking the user id
        int id = 1;
        when(userService.getUserById(id)).thenReturn(ResponseEntity.ok().body(user1));
        ResultActions response = mockMvc.perform(get("/api/v1/User/users/" + id)
                .contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(user1)));
    }

    @Test
    public void createUser() throws Exception {
        when(userService.createUser(user1)).thenReturn(new ResponseEntity<>(user1, HttpStatus.CREATED));
        ResultActions response = mockMvc.perform(post("/api/v1/User/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user1)));
        response.andExpect(MockMvcResultMatchers.status().isCreated());
        response.andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(user1)));
    }


}