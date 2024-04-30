package com.unicalsocial.backend.message;

import com.unicalsocial.backend.models.ConversationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Integer id;
    private ConversationEntity conversationEntity;
    private Instant sentDatetime;
    private String text;
}
