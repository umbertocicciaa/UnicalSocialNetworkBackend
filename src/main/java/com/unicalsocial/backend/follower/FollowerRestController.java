package com.unicalsocial.backend.follower;

import com.unicalsocial.backend.exception.CantFollowSameUserException;
import com.unicalsocial.backend.exception.FollowerNotFoundException;
import com.unicalsocial.backend.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}" + "Follower")
@Tag(name="Follower")
public class FollowerRestController {
    private final FollowerService followerService;

    @CrossOrigin
    @GetMapping(value = "/followers-total/{user_id}")
    ResponseEntity<Long> countFollowers(@PathVariable int user_id) {
        return ResponseEntity.ok(followerService.countFollowers(user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/following-total/{user_id}")
    ResponseEntity<Long> countFollowing(@PathVariable int user_id) {
        return ResponseEntity.ok(this.followerService.countFollowing(user_id));
    }

    @CrossOrigin
    @GetMapping(value = "/following/{user}/{userToFollow}")
    ResponseEntity<Boolean> isFollowing(@PathVariable int user, @PathVariable int userToFollow) {
        try{
            return ResponseEntity.ok(this.followerService.isFollowing(user,userToFollow));
        }catch (FollowerNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin
    @PostMapping(value = "/follow")
    ResponseEntity<FollowerDTO> follow(@RequestBody int user, @RequestBody int userToFollow) {
        try {
            return ResponseEntity.ok(this.followerService.followUser(user, userToFollow));
        }catch (CantFollowSameUserException | UserNotFoundException e){
            return ResponseEntity.badRequest().build();
        }
    }


}
