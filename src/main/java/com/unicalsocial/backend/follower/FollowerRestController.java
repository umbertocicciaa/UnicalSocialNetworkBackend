package com.unicalsocial.backend.follower;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Follower")
@Tag(name = "Follower")
public class FollowerRestController {
    private final FollowerService followerService;

    @CrossOrigin
    @GetMapping(value = "/followers-total/{user_id}")
    ResponseEntity<FollowerNumberResponse> countFollowers(@PathVariable int user_id) {
        return ResponseEntity.ok(followerService.countFollowers(user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/following-total/{user_id}")
    ResponseEntity<FollowingNumberResponse> countFollowing(@PathVariable int user_id) {
        return ResponseEntity.ok(this.followerService.countFollowing(user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/following/{user}/{userToFollow}")
    ResponseEntity<IsFollowingResponse> isFollowing(@PathVariable int user, @PathVariable int userToFollow) {
        return ResponseEntity.ok(this.followerService.isFollowing(user, userToFollow));
    }

    @CrossOrigin
    @PostMapping(value = "/follow")
    ResponseEntity<FollowerCreatedResponse> follow(Authentication authentication, @RequestBody @Valid FollowerRequest userToFollow) {
        return ResponseEntity.ok(this.followerService.followUser(authentication, userToFollow));

    }


}
