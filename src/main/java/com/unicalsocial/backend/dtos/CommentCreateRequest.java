package com.unicalsocial.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class CommentCreateRequest {
    private Integer commentRepliedId;
    @NotNull
    private Integer postId;
    @NotNull
    @NotBlank
    private String comment;
}
