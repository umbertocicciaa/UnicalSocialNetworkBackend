package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserMapper;
import com.unicalsocial.backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

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
    public ResponseEntity<Collection<PostDTO>> getPostOrderedByDateDesc() {
        var post = this.postRepository.findAllByOrderByCreateDatetimeDesc();
        if (post.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(post.stream().map(PostMapper.INSTANCE::postToDto).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Long> countPostByUserId(long userId) {
        var userByUserId = this.userService.getUserById(userId);
        var post_number = this.postRepository.countByCreatedByUserid(UserMapper.INSTANCE.userDtoToUser(userByUserId.getBody()));
        return ResponseEntity.ok().body(post_number);
    }
}
