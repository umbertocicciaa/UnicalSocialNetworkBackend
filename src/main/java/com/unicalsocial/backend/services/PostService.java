package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.*;
import org.springframework.security.core.Authentication;

import java.util.Collection;


public interface PostService {
    PostCreatedResponse createPost(PostCreateRequest request, Authentication authentication);
    PostResponse getPostById(Long id);
    PostDeletedResponse deletePost(long postId, Authentication authentication);
    Collection<PostResponse> getPostOrderedByDateDesc(int page);
    PostByUserResponse countPostByUserId(long userId);
    Collection<PostResponse> getPostOfTypePostByUserId(int page,int user_id);
    Collection<PostResponse> getPostsOfTypeTwitByUserId(int page,int user_id);
    PostCountResponse countAllPost();
    PostResponse addLike(AddLikeRequest request, Authentication authentication);
    TwitCreatedRespose createTwit(TwitCreateRequest request, Authentication authentication);
    Collection<PostResponse> getPostOfTypePost(int page);
    Collection<PostResponse> getPostOfTypeTwit(int page);
    Collection<PostResponse> getPostsOfTypePostFollowings(Authentication authentication,int page);
    Collection<PostResponse> getPostsOfTwitPostFollowings(Authentication authentication,int page);
}
