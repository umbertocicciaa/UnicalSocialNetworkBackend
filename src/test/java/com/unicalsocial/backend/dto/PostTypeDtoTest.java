package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.post_type.PostTypeEntity;
import com.unicalsocial.backend.post_type.PostTypeDTO;
import com.unicalsocial.backend.post_type.PostTypeMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTypeDtoTest {
    @Test
    public void shouldBePostTypeDTO(){
        PostTypeEntity post = new PostTypeEntity();
        post.setPostTypeName("basi di dati");

        PostTypeDTO postTypeDTO = PostTypeMapper.ISTANCE.postTypeToDto(post);

        assertThat(postTypeDTO.getPostTypeName()).isEqualTo(post.getPostTypeName());
    }

    @Test
    public void shouldBePostType(){
        PostTypeDTO postTypeDTO = new PostTypeDTO();
        postTypeDTO.setPostTypeName("basi di dati");

        PostTypeEntity post = PostTypeMapper.ISTANCE.postTypeDtoToPostType(postTypeDTO);
        
        assertThat(postTypeDTO.getPostTypeName()).isEqualTo(post.getPostTypeName());
    }
}
