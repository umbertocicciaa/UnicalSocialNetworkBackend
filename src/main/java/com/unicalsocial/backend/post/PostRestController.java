package com.unicalsocial.backend.post;


import com.unicalsocial.backend.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Post")
@Tag(name="Post")
public class PostRestController {

    private final PostService postService;

    @CrossOrigin
    @GetMapping(value = "/posts")
    public ResponseEntity<Collection<PostResponse>> getPosts(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostOrderedByDateDesc(page));
    }

    @CrossOrigin
    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostResponse> getPostsById(@PathVariable long id) {
        return ResponseEntity.ok(this.postService.getPostById(id));
    }

    @CrossOrigin
    @GetMapping(value = "/posts/posts")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypePost(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id) {
        return ResponseEntity.ok(this.postService.getPostOfTypePostByUserId(page, user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/posts/twits")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypeTwit(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id) {
        return ResponseEntity.ok(this.postService.getPostsOfTypeTwitByUserId(page, user_id));
    }

    @CrossOrigin
    @PostMapping(value = "/posts/posts")
    public ResponseEntity<PostCreatedResponse> createPost(@RequestBody @Valid PostCreateRequest request,Authentication authentication) {
        return ResponseEntity.ok(this.postService.createPost(request,authentication));
    }

    @CrossOrigin
    @PostMapping(value = "/posts/twits")
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid TwitCreateRequest request, Authentication authentication) {
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "/posts/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable long postId) {
        return ResponseEntity.ok(this.postService.deletePost(postId));
    }

    @CrossOrigin
    @PutMapping(value = "/posts/posts/likes")
    public ResponseEntity<PostResponse> addLike(@RequestBody long id,Authentication authentication) {
        var remainingRetries = 3;
        while (remainingRetries > 0) {
            try {
                return ResponseEntity.ok(this.postService.addLike(id, authentication));
            } catch (ObjectOptimisticLockingFailureException e) {
                remainingRetries--;
                if (remainingRetries == 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
        }
        return ResponseEntity.internalServerError().build();
    }

    @CrossOrigin
    @GetMapping(value = "/total-posts/{user_id}")
    public ResponseEntity<Long> countPostsByUserId(@PathVariable long user_id) {
        return ResponseEntity.ok(this.postService.countPostByUserId(user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/total-posts")
    public ResponseEntity<Long> getPostsTotal() {
        return ResponseEntity.ok(this.postService.countAllPost());
    }


}
