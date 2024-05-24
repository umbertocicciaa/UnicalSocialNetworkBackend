package com.unicalsocial.backend.comment;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @CrossOrigin
    @GetMapping(value = "/comments/{post_id}")
    public ResponseEntity<Collection<CommentResponse>> getComment(@PathVariable int post_id,@RequestParam(defaultValue = "0")int page) {
        return ResponseEntity.ok(this.commentService.getCommentByPostId(post_id,page));
    }

    @CrossOrigin
    @DeleteMapping(value = "/comments/{comment_id}")
    public ResponseEntity<CommentDeletedResponse> deleteComment(@PathVariable int comment_id, Authentication authentication) {
        return ResponseEntity.ok(this.commentService.deleteCommentOfPost(comment_id,authentication));
    }

}
