package com.unicalsocial.backend.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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
