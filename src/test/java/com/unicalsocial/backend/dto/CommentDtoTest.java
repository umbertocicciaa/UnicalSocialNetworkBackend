package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.CommentEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentDtoTest {

    @Test
    public void shouldBeCommentDto() {
        CommentEntity comment = new CommentEntity();
        comment.setComment("Bella materia");
        comment.setCreatedByUserid(1);
        comment.setPostId(1);
        comment.setCreatedDatetime(LocalDateTime.now());

        CommentDTO commentDTO = CommentMapper.ISTANCE.commentToCommentDto(comment);
        assertThat(commentDTO).isNotNull();
        assertThat(commentDTO.getComment()).isEqualTo(comment.getComment());
        assertThat(commentDTO.getPostId()).isEqualTo(comment.getPostId());
        assertThat(commentDTO.getCreatedDatetime()).isEqualTo(comment.getCreatedDatetime());
        assertThat(commentDTO.getCreatedByUserid()).isEqualTo(comment.getCreatedByUserid());
    }

    @Test
    public void shouldBeComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment("Bella materia");
        commentDTO.setCreatedByUserid(1);
        commentDTO.setPostId(1);
        commentDTO.setCreatedDatetime(LocalDateTime.now());

        CommentEntity comment = CommentMapper.ISTANCE.commenDtotToComment(commentDTO);
        assertThat(commentDTO).isNotNull();
        assertThat(commentDTO.getComment()).isEqualTo(comment.getComment());
        assertThat(commentDTO.getPostId()).isEqualTo(comment.getPostId());
        assertThat(commentDTO.getCreatedDatetime()).isEqualTo(comment.getCreatedDatetime());
        assertThat(commentDTO.getCreatedByUserid()).isEqualTo(comment.getCreatedByUserid());
    }
}
