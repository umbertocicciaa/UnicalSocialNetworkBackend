package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.token.TokenDTO;
import com.unicalsocial.backend.token.TokenEntity;
import com.unicalsocial.backend.token.TokenMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenDtoTest {
    @Test
    public void shouldBeTokenDTO(){
        String tokenString="asdada";
        TokenEntity token = TokenEntity.builder().token(tokenString).build();

        TokenDTO tokenDTO = TokenMapper.ISTANCE.tokenToTokenDTO(token);

        assertThat(tokenDTO.getToken()).isEqualTo(token.getToken());
    }

    @Test
    public void shouldBePostType(){
        String tokenString="asdada";
        TokenDTO tokenDTO = TokenDTO.builder().token(tokenString).build();
        TokenEntity token = TokenMapper.ISTANCE.tokenDTOToTokenEntity(tokenDTO);
        assertThat(tokenDTO.getToken()).isEqualTo(token.getToken());
    }
}
