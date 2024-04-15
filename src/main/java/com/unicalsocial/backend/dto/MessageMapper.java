package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.models.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper ISTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "text", target = "text")
    @Mapping(source = "sentDatetime", target = "sentDatetime")
    @Mapping(source = "conversationId", target = "conversationId")
    MessageDTO messageToDto(MessageEntity message);

    @Mapping(source = "text", target = "text")
    @Mapping(source = "sentDatetime", target = "sentDatetime")
    @Mapping(source = "conversationId", target = "conversationId")
    MessageEntity messageDtoToMessage(MessageDTO messageDTO);

}
