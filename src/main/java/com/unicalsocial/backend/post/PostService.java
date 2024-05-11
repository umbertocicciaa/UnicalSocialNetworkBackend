package com.unicalsocial.backend.post;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface PostService {
    ResponseEntity<PostDTO>createPost(PostDTO postDTO);
    ResponseEntity<PostDTO>getPostById(Long id);
    ResponseEntity<PostDTO>updatePost(PostDTO postDTO);
    ResponseEntity<PostDTO>deletePost(PostDTO postDTO);
    ResponseEntity<Collection<PostDTO>>getPostOrderedByDateDesc(int page);
    ResponseEntity<Long> countPostByUserId(long userId);
    ResponseEntity<Collection<PostDTO>> getPostOfTypePostByUserId(int page,int user_id);
    ResponseEntity<Collection<PostDTO>> getPostsOfTypeTwitByUserId(int page,int user_id);
    ResponseEntity<Long>countAllPost();
}
