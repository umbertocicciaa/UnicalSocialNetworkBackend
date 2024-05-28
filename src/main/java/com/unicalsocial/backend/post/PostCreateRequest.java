package com.unicalsocial.backend.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {
    @NotEmpty
    @NotNull
    @NotBlank
    @Size(max=2200)
    private String caption;
    @NotNull
    private byte[] mediaFile;
}
