package com.unicalsocial.backend.post;


import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.exception.UserHasLikedPostException;
import com.unicalsocial.backend.exception.UserNotFoundException;
import com.unicalsocial.backend.post_media.PostMediaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}" + "Post")
@Tag(name="Post")
public class PostRestController {

    private final PostService postService;

    @CrossOrigin
    @GetMapping(value = "/posts")
    public ResponseEntity<Collection<PostDTO>> getPosts(@RequestParam(defaultValue = "0") int page) {
        var post = this.postService.getPostOrderedByDateDesc(page);
        if (post.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(post);
    }

    @CrossOrigin
    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostDTO> getPostsById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.postService.getPostById(id));
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping(value = "/posts/posts")
    public ResponseEntity<Collection<PostDTO>> getPostsOfTypePost(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id) {
        var post = this.postService.getPostOfTypePostByUserId(page, user_id);
        if (post.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.postService.getPostOfTypePostByUserId(page, user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/posts/twits")
    public ResponseEntity<Collection<PostDTO>> getPostsOfTypeTwit(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id) {
        var post = this.postService.getPostsOfTypeTwitByUserId(page, user_id);
        if (post.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(this.postService.getPostsOfTypeTwitByUserId(page, user_id));
    }

    @CrossOrigin
    @PostMapping(value = "/posts/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @RequestBody PostMediaDTO postMediaDTO) {
        return ResponseEntity.ok(this.postService.createPost(postDTO, postMediaDTO));
    }

    @CrossOrigin
    @PostMapping(value = "/posts/twits")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return null;
    }

    @CrossOrigin
    @PostMapping(value = "/posts/{postId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable long postId) {
        return ResponseEntity.ok(this.postService.deletePost(postId));
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

    @CrossOrigin
    @PostMapping(value = "/likes")
    public ResponseEntity<PostDTO> addLike(@RequestBody long id, @RequestBody int user_id) {
        var remainingRetries = 3;
        while (remainingRetries > 0) {
            try {
                return ResponseEntity.ok(this.postService.addLike(id, user_id));
            } catch (ObjectOptimisticLockingFailureException e) {
                remainingRetries--;
                if (remainingRetries == 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            } catch (PostNotFoundException | UserNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (UserHasLikedPostException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.internalServerError().build();
    }

}
