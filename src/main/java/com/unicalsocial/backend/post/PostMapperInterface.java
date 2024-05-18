package com.unicalsocial.backend.post;


import com.unicalsocial.backend.post_media.PostMediaEntity;

public interface PostMapperInterface {
    PostCreatedResponse toPostCreatedResponse(PostEntity post, PostMediaEntity postMedia);
    PostResponse toPostResponseWithImage(PostEntity post, PostMediaEntity postMedia);
    PostResponse toPostResponseNoImage(PostEntity post);
}
