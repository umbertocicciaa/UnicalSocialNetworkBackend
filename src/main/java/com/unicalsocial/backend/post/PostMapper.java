package com.unicalsocial.backend.post;


import com.unicalsocial.backend.post_media.PostMediaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostMapper implements PostMapperInterface {

    @Override
    public PostCreatedResponse toPostCreatedResponse(PostEntity post, PostMediaEntity postMedia) {
        return PostCreatedResponse.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .like(post.getLike())
                .pics(postMedia.getMediaFile())
                .postType(post.getPostTypeEntity().getPostTypeName())
                .userId(post.getCreatedByUserid().getId())
                .build();
    }

    @Override
    public PostResponse toPostResponseWithImage(PostEntity post, PostMediaEntity postMedia) {
        return PostResponse.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .like(post.getLike())
                .postType(post.getPostTypeEntity().getPostTypeName())
                .image(postMedia.getMediaFile())
                .user(UserPostResponse.builder()
                                .id(post.getCreatedByUserid().getId())
                                .profileName(post.getCreatedByUserid().getProfileName())
                                .profilePicture(post.getCreatedByUserid().getProfilePicture())
                                .build())
                .build();
    }

    @Override
    public PostResponse toPostResponseNoImage(PostEntity post) {
        return PostResponse.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .postType(post.getPostTypeEntity().getPostTypeName())
                .like(post.getLike())
                .user(UserPostResponse.builder()
                        .id(post.getCreatedByUserid().getId())
                        .profileName(post.getCreatedByUserid().getProfileName())
                        .profilePicture(post.getCreatedByUserid().getProfilePicture())
                        .build())
                .build();
    }

    @Override
    public TwitCreatedRespose toTwitCreatedResponse(PostEntity post) {
        return TwitCreatedRespose.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .like(post.getLike())
                .userId(post.getCreatedByUserid().getId())
                .postId(post.getId())
                .build();
    }
}
