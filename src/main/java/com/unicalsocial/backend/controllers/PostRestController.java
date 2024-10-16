package com.unicalsocial.backend.controllers;


import com.unicalsocial.backend.dtos.*;
import com.unicalsocial.backend.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "Bearer Authentication")
public class PostRestController {

    private final PostService postService;

    @GetMapping(value = "/posts")
    public ResponseEntity<Collection<PostResponse>> getPosts(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostOrderedByDateDesc(page));
    }


    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostResponse> getPostsById(@PathVariable long id) {
        return ResponseEntity.ok(this.postService.getPostById(id));
    }

    @GetMapping(value = "/posts/posts")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypePost(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostOfTypePost(page));
    }

    @GetMapping(value = "/posts/posts/{user_id}")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypePostByUser(@RequestParam(defaultValue = "0") int page, @PathVariable int user_id) {
        return ResponseEntity.ok(this.postService.getPostOfTypePostByUserId(page, user_id));
    }

    @GetMapping(value = "/posts/posts-followings")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypePostFollowings(Authentication authentication, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostsOfTypePostFollowings(authentication,page));
    }

    @GetMapping(value = "/posts/twits")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypeTwits(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostOfTypeTwit(page));
    }

    @GetMapping(value = "/posts/twits/{user_id}")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypeTwitByUser(@RequestParam(defaultValue = "0") int page, @PathVariable int user_id) {
        return ResponseEntity.ok(this.postService.getPostsOfTypeTwitByUserId(page, user_id));
    }

    @GetMapping(value = "/posts/twits-followings")
    public ResponseEntity<Collection<PostResponse>> getPostsOfTypeTwitFollowings(Authentication authentication,@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(this.postService.getPostsOfTwitPostFollowings(authentication,page));
    }

    @PostMapping(value = "/posts/posts")
    public ResponseEntity<PostCreatedResponse> createPost(@RequestBody @Valid PostCreateRequest request, Authentication authentication) {
        return ResponseEntity.ok(this.postService.createPost(request,authentication));
    }

    @PostMapping(value = "/posts/twits")
    public ResponseEntity<TwitCreatedRespose> createTwit(@RequestBody @Valid TwitCreateRequest request, Authentication authentication) {
        return ResponseEntity.ok(this.postService.createTwit(request,authentication));
    }


    @DeleteMapping(value = "/posts/{postId}")
    public ResponseEntity<PostDeletedResponse> deletePost(@PathVariable long postId,Authentication authentication) {
        return ResponseEntity.ok(this.postService.deletePost(postId,authentication));
    }


    @PutMapping(value = "/posts/posts/likes")
    public ResponseEntity<PostResponse> addLike(@RequestBody @Valid AddLikeRequest request, Authentication authentication) {
        var remainingRetries = 100;
        while (remainingRetries > 0) {
            try {
                return ResponseEntity.ok(this.postService.addLike(request, authentication));
            } catch (ObjectOptimisticLockingFailureException e) {
                remainingRetries--;
                if (remainingRetries == 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
        }
        return ResponseEntity.internalServerError().build();
    }


    @GetMapping(value = "/total-posts/{user_id}")
    public ResponseEntity<PostByUserResponse> countPostsByUserId(@PathVariable long user_id) {
        return ResponseEntity.ok(this.postService.countPostByUserId(user_id));
    }

    @GetMapping(value = "/total-posts")
    public ResponseEntity<PostCountResponse> getPostsTotal() {
        return ResponseEntity.ok(this.postService.countAllPost());
    }


}
