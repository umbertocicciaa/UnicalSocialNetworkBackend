package com.unicalsocial.backend.services;

import com.unicalsocial.backend.dtos.CommentCreateRequest;
import com.unicalsocial.backend.dtos.CommentCreatedResponse;
import com.unicalsocial.backend.dtos.CommentDeletedResponse;
import com.unicalsocial.backend.dtos.CommentResponse;
import com.unicalsocial.backend.exception.CantDeleteCommentOfOtherUserException;
import com.unicalsocial.backend.exception.CommentNotFoundException;
import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.mappers.CommentMapper;
import com.unicalsocial.backend.models.CommentEntity;
import com.unicalsocial.backend.models.UserEntity;
import com.unicalsocial.backend.repositories.CommentRepository;
import com.unicalsocial.backend.repositories.PostRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Hidden
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public CommentCreatedResponse createComment(CommentCreateRequest commentReq, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var post = this.postRepository.findById(commentReq.getPostId()).orElseThrow(PostNotFoundException::new);
        var comment = CommentEntity.builder()
                .comment(commentReq.getComment())
                .createdByUserid(user)
                .postEntity(post)
                .build();
        var persistedComment = this.commentRepository.save(comment);
        return this.commentMapper.toCommentCreatedResponse(persistedComment);

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CommentResponse> getCommentByPostId(int postId, int page) {
        final var pageSize = 15;
        final var pageable = PageRequest.of(page, pageSize);
        var comments = this.commentRepository.findByPostEntityIdOrderByCreatedDatetime(postId, pageable);
        return comments.stream().map(this.commentMapper::toCommentResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDeletedResponse deleteCommentOfPost(int commentId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var comment = this.commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (!Objects.equals(comment.getCreatedByUserid().getId(), user.getId()))
            throw new CantDeleteCommentOfOtherUserException();
        this.commentRepository.delete(comment);
        var deleted = this.commentRepository.findById(commentId).isEmpty();
        return CommentDeletedResponse.builder()
                .deleted(deleted)
                .build();
    }
}
