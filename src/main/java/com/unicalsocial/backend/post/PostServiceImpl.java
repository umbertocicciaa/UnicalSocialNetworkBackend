package com.unicalsocial.backend.post;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public ResponseEntity<PostDTO> createPost(PostDTO postDTO) {
        return null;
    }

    @Override
    public ResponseEntity<PostDTO> getPostById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PostDTO> updatePost(PostDTO postDTO) {
        return null;
    }

    @Override
    public ResponseEntity<PostDTO> deletePost(PostDTO postDTO) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<PostDTO>> getPostOrderedByDateDesc() {
        var post = this.postRepository.findAllByOrderByCreateDatetimeDesc();
        if(post.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(post.stream().map(PostMapper.INSTANCE::postToDto).toList());
    }
}
