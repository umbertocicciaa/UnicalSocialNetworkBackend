package com.unicalsocial.backend.post_media;

public interface PostMediaService {
    PostMediaCreateResponse createPostMedia(PostMediaCreateRequest postMediaDTO);
    PostMediaResponse getPostMediaOfPost(long postId);
    Boolean deletePostMedia(long postMediaId);
}
