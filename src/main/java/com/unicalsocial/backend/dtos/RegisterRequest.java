package com.unicalsocial.backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  @NotNull
  @NotEmpty
  private String firstname;
  @NotNull
  @NotEmpty
  private String lastname;
  @NotNull
  @NotEmpty
  private String profilename;
  @NotNull
  @NotEmpty
  private String email;
  @NotNull
  @NotEmpty
  private String password;
}
