package com.unicalsocial.backend.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private int id;
    private String text;
    private LocalDateTime sentDatetime;
    private int conversationId;
}
