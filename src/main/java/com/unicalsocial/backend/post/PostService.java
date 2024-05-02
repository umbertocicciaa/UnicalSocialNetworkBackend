package com.unicalsocial.backend.post;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public interface PostService {
    ResponseEntity<PostDTO>createPost(PostDTO postDTO);
    ResponseEntity<PostDTO>getPostById(Long id);
    ResponseEntity<PostDTO>updatePost(PostDTO postDTO);
    ResponseEntity<PostDTO>deletePost(PostDTO postDTO);
    ResponseEntity<Collection<PostDTO>>getPostOrderedByDateDesc();
}
