package com.unicalsocial.backend.user;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String profileName;
    private String email;
    private Instant signupDate;
    private byte[] profilePicture;
    private String bio;
}
