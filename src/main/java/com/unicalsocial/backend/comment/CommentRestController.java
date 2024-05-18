package com.unicalsocial.backend.comment;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Comment")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name="Comment")
public class CommentRestController {
    private final CommentService commentService;

    @CrossOrigin
    @PostMapping(value = "/comments")
    public ResponseEntity<CommentCreatedResponse> createComment(@RequestBody @Valid CommentCreateRequest comment, Authentication request) {
        return ResponseEntity.ok(this.commentService.createComment(comment, request));
    }

}
