package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.PostEntity;
import com.unicalsocial.backend.models.PostTypeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDtoTest {
    @Test
    public void shouldBePostDTO() {
        PostEntity post = new PostEntity();
        post.setPostType(1);
        post.setCreatedByUserid(1);
        post.setCreateDatetime(LocalDateTime.now());
        post.setCaption("Il mio primo post");

        PostDTO postDTO = PostMapper.ISTANCE.postToDto(post);

        assertThat(postDTO.getPostType()).isEqualTo(post.getPostType());
        assertThat(postDTO.getCaption()).isEqualTo(post.getCaption());
        assertThat(postDTO.getCreateDatetime()).isEqualTo(post.getCreateDatetime());
        assertThat(postDTO.getCreatedByUserid()).isEqualTo(post.getCreatedByUserid());
    }

    @Test
    public void shouldBePost() {
        PostDTO postDTO = new PostDTO();
        postDTO.setCaption("Il mio primo post");
        postDTO.setCreateDatetime(LocalDateTime.now());
        postDTO.setCreatedByUserid(1);
        postDTO.setPostType(1);

        PostEntity post = PostMapper.ISTANCE.postDtoToPost(postDTO);

        assertThat(postDTO.getPostType()).isEqualTo(post.getPostType());
        assertThat(postDTO.getCaption()).isEqualTo(post.getCaption());
        assertThat(postDTO.getCreateDatetime()).isEqualTo(post.getCreateDatetime());
        assertThat(postDTO.getCreatedByUserid()).isEqualTo(post.getCreatedByUserid());
    }
}
