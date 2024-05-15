package com.unicalsocial.backend.post_media;

public interface PostMediaService {
    PostMediaDTO createPostMedia(PostMediaDTO postMediaDTO);
    PostMediaDTO getPostMediaOfPost(long postId);
    Boolean deletePostMedia(long postMediaId);
}
