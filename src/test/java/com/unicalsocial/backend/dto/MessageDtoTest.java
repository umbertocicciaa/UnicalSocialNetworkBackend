package com.unicalsocial.backend.dto;

import com.unicalsocial.backend.message.MessageDTO;
import com.unicalsocial.backend.message.MessageMapper;
import com.unicalsocial.backend.models.MessageEntity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MessageDtoTest {
    @Test
    public void shouldBeMessageDto() {
        MessageEntity message = new MessageEntity();
        message.setText("Ciao ciccio");
        message.setConversationId(1);
        message.setSentDatetime(LocalDateTime.now());

        MessageDTO messageDTO = MessageMapper.ISTANCE.messageToDto(message);

        assertThat(messageDTO.getText()).isEqualTo(message.getText());
        assertThat(messageDTO.getConversationId()).isEqualTo(message.getConversationId());
        assertThat(messageDTO.getSentDatetime()).isEqualTo(message.getSentDatetime());
    }

    @Test
    public void shouldBeMessage() {
        MessageDTO messageDTO = new MessageDTO(1,"Ciao ciccio", LocalDateTime.now(), 1);

        MessageEntity message = MessageMapper.ISTANCE.messageDtoToMessage(messageDTO);

        assertThat(messageDTO.getText()).isEqualTo(message.getText());
        assertThat(messageDTO.getConversationId()).isEqualTo(message.getConversationId());
        assertThat(messageDTO.getSentDatetime()).isEqualTo(message.getSentDatetime());
    }
}
