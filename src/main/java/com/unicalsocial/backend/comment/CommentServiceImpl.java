package com.unicalsocial.backend.comment;

import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.post.PostRepository;
import com.unicalsocial.backend.user.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var commentToReply = this.commentRepository.findById(commentReq.getCommentRepliedId());
        if(commentToReply.isPresent()) {
            var comment = CommentEntity.builder()
                    .comment(commentReq.getComment())
                    .createdByUserid(user)
                    .postEntity(post)
                    .commentEntityRepliedTo(commentToReply.get())
                    .build();
            var persistedComment = this.commentRepository.save(comment);
            return this.commentMapper.toCommentCreatedResponse(persistedComment);
        }
        var comment = CommentEntity.builder()
                .comment(commentReq.getComment())
                .createdByUserid(user)
                .postEntity(post)
                .build();
        var persistedComment = this.commentRepository.save(comment);
        return this.commentMapper.toCommentCreatedResponse(persistedComment);
    }
}
