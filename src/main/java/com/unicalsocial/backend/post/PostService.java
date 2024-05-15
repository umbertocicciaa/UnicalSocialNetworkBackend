package com.unicalsocial.backend.post;

import com.unicalsocial.backend.post_media.PostMediaDTO;

import java.util.Collection;

public interface PostService {
    PostDTO createPost(PostDTO postDTO, PostMediaDTO postMediaDTO);
    PostDTO getPostById(Long id);
    Boolean deletePost(long postId);

    Collection<PostDTO> getPostOrderedByDateDesc(int page);
    Long countPostByUserId(long userId);
    Collection<PostDTO> getPostOfTypePostByUserId(int page,int user_id);
    Collection<PostDTO> getPostsOfTypeTwitByUserId(int page,int user_id);
    Long countAllPost();

    PostDTO addLike(long postId,long userId);
}
