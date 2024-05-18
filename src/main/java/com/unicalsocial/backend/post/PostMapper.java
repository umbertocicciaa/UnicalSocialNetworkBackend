package com.unicalsocial.backend.post;


import com.unicalsocial.backend.exception.PostTypeNotFoundException;
import com.unicalsocial.backend.post_type.PostTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostMapper implements PostMapperInterface{

    private final PostTypeRepository postTypeRepository;

    public PostEntity toPost(PostCreateRequest request) {
        var postType = postTypeRepository.findByPostTypeName("post").orElseThrow(PostTypeNotFoundException::new);
        return PostEntity.builder()
                .like(0)
                .postTypeEntity(postType)
                .caption(request.getCaption())
                .build();
    }

    public PostCreatedResponse toPostCreatedResponse(PostEntity postEntity) {
        return  PostCreatedResponse.builder()
                .caption(postEntity.getCaption())
                .id(postEntity.getId())
                .like(postEntity.getLike())
                .postTypeEntity(postEntity.getPostTypeEntity())
                .build();
    }

    public PostResponse toPostResponse(PostEntity postEntity) {
        var postType = this.postTypeRepository.findById(postEntity.getPostTypeEntity().getId()).orElseThrow(PostTypeNotFoundException::new);
        return PostResponse.builder()
                .caption(postEntity.getCaption())
                .id(postEntity.getId())
                .like(postEntity.getLike())
                .postType(postType.getPostTypeName())
                .build();
    }

}
