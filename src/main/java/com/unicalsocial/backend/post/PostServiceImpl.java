package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserDTO;
import com.unicalsocial.backend.user.UserMapper;
import com.unicalsocial.backend.user.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
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
    private final PostTypeService postTypeService;
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
    public ResponseEntity<Collection<PostDTO>> getPostOrderedByDateDesc(int page) {
        final var size=10;
        var pageable = PageRequest.of(page, size);
        var post = this.postRepository.findAllByOrderByCreateDatetimeDesc(pageable);
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Collection<PostDTO>> getPostOfTypePostByUserId(int page,int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userService.getUserById(userid);
        var postTypePostDto = this.postTypeService.findPostTypeByName(PostTypeStringEnum.post.toString());
        return getCollectionResponseEntity(pageable, postTypePostDto,user);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Collection<PostDTO>> getPostsOfTypeTwitByUserId(int page,int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userService.getUserById(userid);
        var postTypePostDto = this.postTypeService.findPostTypeByName(PostTypeStringEnum.twit.toString());
        return getCollectionResponseEntity(pageable, postTypePostDto,user);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Long> countAllPost() {
        var numberOfPosts = this.postRepository.count();
        return ResponseEntity.ok().body(numberOfPosts);
    }

    private ResponseEntity<Collection<PostDTO>> getCollectionResponseEntity(PageRequest pageable, @NotNull ResponseEntity<PostTypeDTO> postTypePostDto, @NotNull ResponseEntity<UserDTO> user) {
        var postTypePost = PostTypeMapper.INSTANCE.postTypeDtoToPostType(postTypePostDto.getBody());
        var userEntity = UserMapper.INSTANCE.userDtoToUser(user.getBody());
        var posts = this.postRepository.findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(postTypePost,userEntity,pageable);
        if(posts.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(posts.stream().map(PostMapper.INSTANCE::postToDto).collect(Collectors.toList()));
    }
}
