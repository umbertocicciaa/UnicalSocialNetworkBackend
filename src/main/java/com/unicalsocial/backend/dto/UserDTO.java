package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String profileName;
    private LocalDateTime signupDate;
}
