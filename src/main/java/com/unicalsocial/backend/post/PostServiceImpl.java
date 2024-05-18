package com.unicalsocial.backend.post;

import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.exception.UserHasLikedPostException;
import com.unicalsocial.backend.mipiace.MipiaceService;
import com.unicalsocial.backend.post_media.PostMediaCreateRequest;
import com.unicalsocial.backend.post_media.PostMediaService;
import com.unicalsocial.backend.post_type.PostTypeDTO;
import com.unicalsocial.backend.post_type.PostTypeMapper;
import com.unicalsocial.backend.post_type.PostTypeService;
import com.unicalsocial.backend.post_type.PostTypeStringEnum;
import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserMapperInterface;
import com.unicalsocial.backend.user.UserResponse;
import com.unicalsocial.backend.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Hidden
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMediaService postMediaService;
    private final MipiaceService mipiaceService;
    private final UserService userService;
    private final PostTypeService postTypeService;
    private final PostMapperInterface postMapper;
    private final UserMapperInterface userMapper;

    @Override
    @Transactional
    public PostCreatedResponse createPost(PostCreateRequest request, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var postEntity = postMapper.toPost(request);
        postEntity.setCreatedByUserid(user);
        var post = this.postRepository.save(postEntity);
        this.postMediaService.createPostMedia(PostMediaCreateRequest.builder().postEntity(post).mediaFile(request.getMediaFile()).build());
        return postMapper.toPostCreatedResponse(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        var postId = Math.toIntExact(id);
        var post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return this.postMapper.toPostResponse(post);
    }

    @Override
    public Boolean deletePost(long postId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostOrderedByDateDesc(int page) {
        final var size = 10;
        var pageable = PageRequest.of(page, size);
        var posts = this.postRepository.findAllByOrderByCreateDatetimeDesc(pageable);
        return posts.stream().map(postMapper::toPostResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPostByUserId(long userId) {
        var userByUserId = this.userService.getUserById(userId);
        return this.postRepository.countByCreatedByUserid(this.userMapper.toUserEntity(userByUserId));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostOfTypePostByUserId(int page, int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userService.getUserById(userid);
        var postTypePostDto = this.postTypeService.findPostTypeByName(PostTypeStringEnum.post.toString());
        return getCollectionResponseEntity(pageable, postTypePostDto, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostsOfTypeTwitByUserId(int page, int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userService.getUserById(userid);
        var postTypePostDto = this.postTypeService.findPostTypeByName(PostTypeStringEnum.twit.toString());
        return getCollectionResponseEntity(pageable, postTypePostDto, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAllPost() {
        return this.postRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PostResponse addLike(AddLikeRequest request, Authentication authentication) {
        if(this.mipiaceService.existMipiace(Math.toIntExact(request.getPostId()),authentication))
            throw new UserHasLikedPostException();
        var id = Math.toIntExact(request.getPostId());
        var post = this.postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setLike(post.getLike() + 1);
        var postToReturn = this.postRepository.save(post);
        this.mipiaceService.createMipiace(Math.toIntExact(request.getPostId()),authentication);
        return postMapper.toPostResponse(postToReturn);
    }

    private Collection<PostResponse> getCollectionResponseEntity(PageRequest pageable, @NotNull PostTypeDTO postTypePostDto, @NotNull UserResponse user) {
        var postTypePost = PostTypeMapper.INSTANCE.postTypeDtoToPostType(postTypePostDto);
        var userEntity = this.userMapper.toUserEntity(user);
        var posts = this.postRepository.findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(postTypePost, userEntity, pageable);
        return posts.stream().map(postMapper::toPostResponse).collect(Collectors.toList());
    }
}
