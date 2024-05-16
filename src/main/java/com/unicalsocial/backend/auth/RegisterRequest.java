package com.unicalsocial.backend.auth;

import com.unicalsocial.backend.user.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String profilename;
  private String email;
  private String password;
  private RoleEnum role;
}
