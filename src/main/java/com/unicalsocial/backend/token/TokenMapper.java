package com.unicalsocial.backend.token;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TokenMapper {
    TokenMapper ISTANCE = Mappers.getMapper(TokenMapper.class);
    TokenDTO tokenToTokenDTO(TokenEntity token);
    TokenEntity tokenDTOToTokenEntity(TokenDTO tokenDTO);
}
