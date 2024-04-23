package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.like.LikeDTO;
import com.unicalsocial.backend.like.LikeMapper;
import com.unicalsocial.backend.like.LikeEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LikeDtoTest {
    @Test
    public void shouldBeLikeDto() {
        LikeEntity like = new LikeEntity();
        like.setPostId(1);
        like.setUserId(1);

        LikeDTO likeDTO = LikeMapper.ISTANCE.likeToDto(like);

        assertThat(likeDTO.getPostId()).isEqualTo(like.getPostId());
        assertThat(likeDTO.getUserId()).isEqualTo(like.getUserId());
    }

    @Test
    public void shouldBeLike() {
        LikeDTO likeDTO = new LikeDTO(1, 1);

        LikeEntity like = LikeMapper.ISTANCE.likeDtoToLike(likeDTO);

        assertThat(likeDTO.getPostId()).isEqualTo(like.getPostId());
        assertThat(likeDTO.getUserId()).isEqualTo(like.getUserId());
    }
}
