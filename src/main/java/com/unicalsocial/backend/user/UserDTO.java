package com.unicalsocial.backend.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String profileName;
    @Email
    private String email;
    private LocalDateTime signupDate;
}
