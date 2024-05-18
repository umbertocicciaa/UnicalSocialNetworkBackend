package com.unicalsocial.backend.post_media;

public interface PostMediaMapperInterface {
     PostMediaEntity toPostMediaEntity(PostMediaCreateRequest request);

     PostMediaResponse toPostMediaResponse(PostMediaEntity postMediaEntity);

     PostMediaCreateResponse toPostMediaCreateResponse(PostMediaEntity postMediaEntity);
}
