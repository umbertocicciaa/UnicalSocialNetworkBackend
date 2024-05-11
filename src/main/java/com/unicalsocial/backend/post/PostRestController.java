package com.unicalsocial.backend.post;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("${api.endpoint}"+"Post")
public class PostRestController {

    private final PostService postService;

    @CrossOrigin
    @GetMapping(value = "/posts")
    public ResponseEntity<Collection<PostDTO>> getPosts(@RequestParam(defaultValue = "0") int page){
        return this.postService.getPostOrderedByDateDesc(page);
    }

    @CrossOrigin
    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostDTO> getPostsById(@PathVariable long id){
        return this.postService.getPostById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/posts/countPost/{user_id}")
    public ResponseEntity<Long> countPostsByUserId(@PathVariable long user_id){
        return this.postService.countPostByUserId(user_id);
    }

    @CrossOrigin
    @GetMapping(value = "/posts/post")
    public ResponseEntity<Collection<PostDTO>> getPostsOfTypePost(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id){
        return this.postService.getPostOfTypePostByUserId(page,user_id);
    }


    @CrossOrigin
    @GetMapping(value = "/posts/twit")
    public ResponseEntity<Collection<PostDTO>> getPostsOfTypeTwit(@RequestParam(defaultValue = "0") int page, @RequestParam int user_id){
        return this.postService.getPostsOfTypeTwitByUserId(page,user_id);
    }

    @CrossOrigin
    @GetMapping(value = "/posts/getTotal")
    public ResponseEntity<Long> getPostsOfTypeTwit(){
        return this.postService.countAllPost();
    }

}
