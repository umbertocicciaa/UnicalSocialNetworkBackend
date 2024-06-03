package com.unicalsocial.backend.message;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.Collection;
@Hidden
public interface MessageService {
    Message saveMessage(Message message);
    Collection<Message> getMessagesByConversationId(Integer conversationId);
}
