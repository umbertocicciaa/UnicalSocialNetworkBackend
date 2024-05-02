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
    public ResponseEntity<Collection<PostDTO>> getPosts(){
        return this.postService.getPostOrderedByDateDesc();
    }

    @CrossOrigin
    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostDTO> getPostsById(@PathVariable long id){
        return this.postService.getPostById(id);
    }


}
