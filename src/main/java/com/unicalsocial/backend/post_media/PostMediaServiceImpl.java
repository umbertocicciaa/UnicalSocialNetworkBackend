package com.unicalsocial.backend.post_media;

import com.unicalsocial.backend.exception.PostMediaNotFoundException;
import com.unicalsocial.backend.post.PostEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Transactional
@Service
@Hidden
public class PostMediaServiceImpl implements PostMediaService{

    private final PostMediaRepository postMediaRepository;
    private final PostMediaMapperInterface postMediaMapper;

    @Override
    @Transactional
    public PostMediaCreateResponse createPostMedia(PostMediaCreateRequest request) {
        var postMedia = postMediaMapper.toPostMediaEntity(request);
        var media = this.postMediaRepository.save(postMedia);
        return postMediaMapper.toPostMediaCreateResponse(media);
    }

    @Override
    @Transactional(readOnly = true)
    public PostMediaResponse getPostMediaOfPost(long id) {
        var postEntity = PostEntity.builder().id((int)id).build();
        var media=postMediaRepository.findByPostEntity(postEntity).orElseThrow(PostMediaNotFoundException::new);
        return postMediaMapper.toPostMediaResponse(media);
    }


    @Override
    @Transactional
    public Boolean deletePostMedia(long postMediaId) {
            var postMediaEntity = this.postMediaRepository.findById((int) postMediaId).orElseThrow(PostMediaNotFoundException::new);
            this.postMediaRepository.delete(postMediaEntity);
            return true;
    }
}
