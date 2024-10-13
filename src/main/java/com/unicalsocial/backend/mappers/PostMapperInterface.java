package com.unicalsocial.backend.mappers;


import com.unicalsocial.backend.dtos.PostCreatedResponse;
import com.unicalsocial.backend.dtos.PostResponse;
import com.unicalsocial.backend.dtos.TwitCreatedRespose;
import com.unicalsocial.backend.models.PostEntity;
import com.unicalsocial.backend.models.PostMediaEntity;

public interface PostMapperInterface {
    PostCreatedResponse toPostCreatedResponse(PostEntity post, PostMediaEntity postMedia);
    PostResponse toPostResponseWithImage(PostEntity post, PostMediaEntity postMedia);
    PostResponse toPostResponseNoImage(PostEntity post);
    TwitCreatedRespose toTwitCreatedResponse(PostEntity post);
}
