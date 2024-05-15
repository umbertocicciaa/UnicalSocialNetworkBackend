package com.unicalsocial.backend.post;

import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.exception.UserHasLikedPostException;
import com.unicalsocial.backend.mipiace.MipiaceService;
import com.unicalsocial.backend.post_media.PostMediaDTO;
import com.unicalsocial.backend.post_media.PostMediaService;
import com.unicalsocial.backend.post_type.PostTypeDTO;
import com.unicalsocial.backend.post_type.PostTypeMapper;
import com.unicalsocial.backend.post_type.PostTypeService;
import com.unicalsocial.backend.post_type.PostTypeStringEnum;
import com.unicalsocial.backend.user.UserDTO;
import com.unicalsocial.backend.user.UserMapper;
import com.unicalsocial.backend.user.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
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
    private final PostTypeService postTypeService;
    private final PostMediaService postMediaService;
    private final UserService userService;
    private final MipiaceService mipiaceService;

    @Override
    @Transactional
    public PostDTO createPost(PostDTO postDTO, PostMediaDTO postMediaDTO) {
        var post = this.postRepository.save(PostMapper.INSTANCE.postDtoToPost(postDTO));
        this.postMediaService.createPostMedia(postMediaDTO);
        return PostMapper.INSTANCE.postToDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getPostById(Long id) {
        var postId = Math.toIntExact(id);
        var post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return PostMapper.INSTANCE.postToDto(post);
    }

    @Override
    public Boolean deletePost(long postId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostDTO> getPostOrderedByDateDesc(int page) {
        final var size = 10;
        var pageable = PageRequest.of(page, size);
        var post = this.postRepository.findAllByOrderByCreateDatetimeDesc(pageable);
        return post.stream().map(PostMapper.INSTANCE::postToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPostByUserId(long userId) {
        var userByUserId = this.userService.getUserById(userId);
        return this.postRepository.countByCreatedByUserid(UserMapper.INSTANCE.userDtoToUser(userByUserId));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostDTO> getPostOfTypePostByUserId(int page, int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userService.getUserById(userid);
        var postTypePostDto = this.postTypeService.findPostTypeByName(PostTypeStringEnum.post.toString());
        return getCollectionResponseEntity(pageable, postTypePostDto, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostDTO> getPostsOfTypeTwitByUserId(int page, int userid) {
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
    public PostDTO addLike(long postId,long userId) {
        if(this.mipiaceService.existMipiace(Math.toIntExact(userId),Math.toIntExact(postId)))
            throw new UserHasLikedPostException();
        var id = Math.toIntExact(postId);
        var post = this.postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        var user = this.userService.getUserById(userId);
        post.setLike(post.getLike() + 1);
        var postToReturn = this.postRepository.save(post);
        this.mipiaceService.createMipiace(Math.toIntExact(userId),Math.toIntExact(postId));
        return PostMapper.INSTANCE.postToDto(postToReturn);
    }

    private Collection<PostDTO> getCollectionResponseEntity(PageRequest pageable, @NotNull PostTypeDTO postTypePostDto, @NotNull UserDTO user) {
        var postTypePost = PostTypeMapper.INSTANCE.postTypeDtoToPostType(postTypePostDto);
        var userEntity = UserMapper.INSTANCE.userDtoToUser(user);
        var posts = this.postRepository.findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(postTypePost, userEntity, pageable);
        return posts.stream().map(PostMapper.INSTANCE::postToDto).collect(Collectors.toList());
    }
}
