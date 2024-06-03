package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserResponse;
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
@RequestMapping("/api/v1/Follower")
@Tag(name = "Follower")
@SecurityRequirement(name = "Bearer Authentication")
public class FollowerRestController {
    private final FollowerService followerService;

    @GetMapping(value = "/following")
    ResponseEntity<Collection<UserResponse>> getFollowers(Authentication authentication, @RequestParam(defaultValue = "0") int page) {
        var loggedUser = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(this.followerService.getFollowingUsers(loggedUser, page));
    }

    @GetMapping(value = "/followers-total/{user_id}")
    ResponseEntity<FollowerNumberResponse> countFollowers(@PathVariable int user_id) {
        return ResponseEntity.ok(followerService.countFollowers(user_id));
    }


    @GetMapping(value = "/following-total/{user_id}")
    ResponseEntity<FollowingNumberResponse> countFollowing(@PathVariable int user_id) {
        return ResponseEntity.ok(this.followerService.countFollowing(user_id));
    }


    @GetMapping(value = "/following/{user}/{userToFollow}")
    ResponseEntity<IsFollowingResponse> isFollowing(@PathVariable int user, @PathVariable int userToFollow) {
        return ResponseEntity.ok(this.followerService.isFollowing(user, userToFollow));
    }


    @PostMapping(value = "/follow")
    ResponseEntity<FollowerCreatedResponse> follow(Authentication authentication, @RequestBody @Valid FollowerRequest userToFollow) {
        return ResponseEntity.ok(this.followerService.followUser(authentication, userToFollow));

    }


}
