package com.unicalsocial.backend.post;


public interface PostMapperInterface {

     PostEntity toPost(PostCreateRequest request);

     PostCreatedResponse toPostCreatedResponse(PostEntity postEntity);

     PostResponse toPostResponse(PostEntity postEntity);
}
