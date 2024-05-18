package com.unicalsocial.backend.post_media;

import org.springframework.stereotype.Service;

@Service
public class PostMediaMapper implements PostMediaMapperInterface{

    public PostMediaEntity toPostMediaEntity(PostMediaCreateRequest request){
        return PostMediaEntity.builder()
                .mediaFile(request.getMediaFile())
                .postEntity(request.getPostEntity())
                .build();
    }

    public PostMediaResponse toPostMediaResponse(PostMediaEntity postMediaEntity){
        return PostMediaResponse.builder()
                .mediaFile(postMediaEntity.getMediaFile())
                .postId(postMediaEntity.getPostEntity().getId())
                .id(postMediaEntity.getId())
                .build();
    }

    public PostMediaCreateResponse toPostMediaCreateResponse(PostMediaEntity postMediaEntity){
        return PostMediaCreateResponse.builder()
                .mediaFile(postMediaEntity.getMediaFile())
                .id(postMediaEntity.getId())
                .postId(postMediaEntity.getPostEntity().getId())
                .build();
    }


}
