package com.unicalsocial.backend.post;

import com.unicalsocial.backend.exception.*;
import com.unicalsocial.backend.mipiace.Mipiace;
import com.unicalsocial.backend.mipiace.MipiaceId;
import com.unicalsocial.backend.mipiace.MipiaceRepository;
import com.unicalsocial.backend.post_media.PostMediaEntity;
import com.unicalsocial.backend.post_media.PostMediaRepository;
import com.unicalsocial.backend.post_type.*;
import com.unicalsocial.backend.user.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Hidden
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final MipiaceRepository mipiaceRepository;
    private final UserRepository userRepository;
    private final PostTypeRepository postTypeRepository;
    private final PostMapperInterface postMapper;

    @Override
    @Transactional
    public PostCreatedResponse createPost(PostCreateRequest request, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.post.toString()).orElseThrow(PostTypeNotFoundException::new);
        var postEntity = PostEntity.builder()
                .postTypeEntity(postType)
                .createdByUserid(user)
                .caption(request.getCaption())
                .like(0)
                .version(0)
                .build();
        var post = this.postRepository.save(postEntity);
        var postMedia = PostMediaEntity.builder()
                .postEntity(post)
                .mediaFile(request.getMediaFile())
                .build();
        this.postMediaRepository.save(postMedia);
        return postMapper.toPostCreatedResponse(post, postMedia);
    }

    @Override
    @Transactional
    public TwitCreatedRespose createTwit(TwitCreateRequest request, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.twit.toString()).orElseThrow(PostTypeNotFoundException::new);
        var postEntity = PostEntity.builder()
                .postTypeEntity(postType)
                .createdByUserid(user)
                .caption(request.getCaption())
                .like(0)
                .version(0)
                .build();
        var post = this.postRepository.save(postEntity);
        return this.postMapper.toTwitCreatedResponse(post);
    }

    @Override
    public Collection<PostResponse> getPostOfTypePost(int page) {
        final var size = 50;
        final var pageable = PageRequest.of(page, size);
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.post.toString()).orElseThrow(PostTypeNotFoundException::new);
        var posts = this.postRepository.findAllByPostTypeEntityOrderByLikeDesc(postType,pageable);
        return getPostResponses(posts);
    }

    @Override
    public Collection<PostResponse> getPostOfTypeTwit(int page) {
        final var size = 20;
        final var pageable = PageRequest.of(page, size);
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.twit.toString()).orElseThrow(PostTypeNotFoundException::new);
        var posts = this.postRepository.findAllByPostTypeEntityOrderByLikeDesc(postType,pageable);
        return posts.stream().map(postMapper::toPostResponseNoImage).collect(Collectors.toList());
    }

    @Override
    public Collection<PostResponse> getPostsOfTypePostFollowings(Authentication authentication,int page) {
        var user = (UserEntity) authentication.getPrincipal();
        if(user==null)
            throw new UserNotFoundException();
        final var size = 20;
        final var pageable = PageRequest.of(page, size);
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.post.toString()).orElseThrow(PostTypeNotFoundException::new);
        var postResEntity= this.postRepository.findPostsByPostTypeAndFollowedUsers(postType.getId(), user.getId(),pageable);
        var postResponseResult = new ArrayList<PostResponse>();
        for(var post : postResEntity){
            var postMedia = this.postMediaRepository.findByPostEntity(post);
            postMedia.ifPresent(postMediaEntity -> postResponseResult.add(this.postMapper.toPostResponseWithImage(post, postMediaEntity)));
        }
        return postResponseResult;
    }

    @Override
    public Collection<PostResponse> getPostsOfTwitPostFollowings(Authentication authentication,int page) {
        var user = (UserEntity) authentication.getPrincipal();
        if(user==null)
            throw new UserNotFoundException();
        final var size = 20;
        final var pageable = PageRequest.of(page, size);
        var postType = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.twit.toString()).orElseThrow(PostTypeNotFoundException::new);
        return this.postRepository.findPostsByPostTypeAndFollowedUsers(postType.getId(), user.getId(),pageable).stream().map(postMapper::toPostResponseNoImage).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        var postId = Math.toIntExact(id);
        var post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        var postMedia = this.postMediaRepository.findByPostEntity(post);
        if (postMedia.isPresent())
            return this.postMapper.toPostResponseWithImage(post, postMedia.get());
        return this.postMapper.toPostResponseNoImage(post);
    }

    @Override
    @Transactional
    public PostDeletedResponse deletePost(long postId,Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var post = this.postRepository.findById(Math.toIntExact(postId)).orElseThrow(PostNotFoundException::new);
        if(!Objects.equals(post.getCreatedByUserid().getId(), user.getId()))
            throw new CantDeletePostOfOtherUserException();
        this.mipiaceRepository.deleteMipiaceByPostId(post.getId());
        var media = this.postMediaRepository.findByPostEntity(post);
        media.ifPresent(this.postMediaRepository::delete);
        this.postRepository.deleteById(Math.toIntExact(postId));
        return new PostDeletedResponse(this.postRepository.findById(Math.toIntExact(postId)).isEmpty());
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostOrderedByDateDesc(int page) {
        final var size = 10;
        var pageable = PageRequest.of(page, size);
        var posts = this.postRepository.findAllByOrderByCreateDatetimeDesc(pageable);
        return getPostResponses(posts);
    }

    @Override
    @Transactional(readOnly = true)
    public PostByUserResponse countPostByUserId(long userId) {
        var userByUserId = this.userRepository.findById(Math.toIntExact(userId)).orElseThrow(UserNotFoundException::new);
        return PostByUserResponse.builder()
                .post(this.postRepository.countByCreatedByUserid(userByUserId))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostOfTypePostByUserId(int page, int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userRepository.findById(userid).orElseThrow(UserNotFoundException::new);
        var postTypePost = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.post.toString()).orElseThrow(PostTypeNotFoundException::new);
        var posts = this.postRepository.findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(postTypePost, user, pageable);
        return getPostResponses(posts);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PostResponse> getPostsOfTypeTwitByUserId(int page, int userid) {
        final var size = 9;
        var pageable = PageRequest.of(page, size);
        var user = this.userRepository.findById(userid).orElseThrow(UserNotFoundException::new);
        var postTypeTwit = this.postTypeRepository.findByPostTypeName(PostTypeStringEnum.twit.toString()).orElseThrow(PostTypeNotFoundException::new);
        var posts = this.postRepository.findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(postTypeTwit, user, pageable);
        return getPostResponses(posts);
    }

    @Override
    @Transactional(readOnly = true)
    public PostCountResponse countAllPost() {
        return PostCountResponse.builder()
                .postCount(this.postRepository.count())
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PostResponse addLike(AddLikeRequest request, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        if (this.mipiaceRepository.findById(MipiaceId.builder()
                .userId(user.getId())
                .postId(request.getPostId())
                .build()).isPresent())
            throw new UserCantLikeTwoTimeSamePost();
        var id = Math.toIntExact(request.getPostId());
        var post = this.postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setLike(post.getLike() + 1);
        var postToReturn = this.postRepository.save(post);
        var mipiaceid = MipiaceId.builder()
                .postId(post.getId())
                .userId(user.getId())
                .build();
        var mipiace = Mipiace.builder()
                .id(mipiaceid)
                .post(post)
                .user(user)
                .build();
        this.mipiaceRepository.save(mipiace);
        var postMedia = this.postMediaRepository.findByPostEntity(postToReturn);
        if (postMedia.isPresent())
            return postMapper.toPostResponseWithImage(postToReturn, postMedia.get());
        return this.postMapper.toPostResponseNoImage(post);
    }


    @NotNull
    private Collection<PostResponse> getPostResponses(Slice<PostEntity> posts) {
        Collection<PostResponse> postResponses = new ArrayList<>();
        for (var post : posts) {
            var postMedia = this.postMediaRepository.findByPostEntity(post);
            if (postMedia.isPresent())
                postResponses.add(this.postMapper.toPostResponseWithImage(post, postMedia.get()));
            else
                postResponses.add(this.postMapper.toPostResponseNoImage(post));
        }
        return postResponses;
    }
}
