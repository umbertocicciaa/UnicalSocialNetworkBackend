package com.unicalsocial.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private int id;
    private String text;
    private LocalDateTime sentDatetime;
    private int conversationId;
}
