package com.unicalsocial.backend.follower;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}" + "Followers")
public class FollowerRestController {
    private final FollowerService followerService;

    @CrossOrigin
    @GetMapping(value = "/followers/{user_id}")
    ResponseEntity<Long> countFollowers(@PathVariable int user_id) {
        return this.followerService.countFollowers(user_id);
    }

    @CrossOrigin
    @GetMapping(value = "/following/{user_id}")
    ResponseEntity<Long> countFollowing(@PathVariable int user_id) {
        return this.followerService.countFollowing(user_id);
    }
}
