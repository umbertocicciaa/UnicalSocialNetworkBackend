package com.unicalsocial.backend.message;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO messageToDto(MessageEntity message);

    MessageEntity messageDtoToMessage(MessageDTO messageDTO);

}
