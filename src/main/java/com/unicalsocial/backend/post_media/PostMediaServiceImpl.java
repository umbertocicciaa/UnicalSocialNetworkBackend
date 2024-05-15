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

    @Override
    @Transactional
    public PostMediaDTO createPostMedia(PostMediaDTO postMediaDTO) {
        var media = this.postMediaRepository.save(PostMediaMapper.INSTANCE.postMediaDTOToPostMedia(postMediaDTO));
        return PostMediaMapper.INSTANCE.postMediaToPostMediaDTO(media);
    }

    @Override
    @Transactional(readOnly = true)
    public PostMediaDTO getPostMediaOfPost(long id) {
        var postEntity = PostEntity.builder().id((int)id).build();
        var media=postMediaRepository.findByPostEntity(postEntity);
        return media.map(PostMediaMapper.INSTANCE::postMediaToPostMediaDTO).orElseThrow(PostMediaNotFoundException::new);
    }


    @Override
    @Transactional
    public Boolean deletePostMedia(long postMediaId) {
            var postMediaEntity = this.postMediaRepository.findById((int) postMediaId).orElseThrow(PostMediaNotFoundException::new);
            this.postMediaRepository.delete(postMediaEntity);
            return true;
    }
}
