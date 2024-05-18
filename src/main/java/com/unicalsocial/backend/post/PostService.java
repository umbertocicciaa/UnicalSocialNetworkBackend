package com.unicalsocial.backend.post;

import org.springframework.security.core.Authentication;

import java.util.Collection;


public interface PostService {
    PostCreatedResponse createPost(PostCreateRequest request, Authentication authentication);
    PostResponse getPostById(Long id);
    Boolean deletePost(long postId);
    Collection<PostResponse> getPostOrderedByDateDesc(int page);
    Long countPostByUserId(long userId);
    Collection<PostResponse> getPostOfTypePostByUserId(int page,int user_id);
    Collection<PostResponse> getPostsOfTypeTwitByUserId(int page,int user_id);
    Long countAllPost();
    PostResponse addLike(long postId, Authentication authentication);
}
