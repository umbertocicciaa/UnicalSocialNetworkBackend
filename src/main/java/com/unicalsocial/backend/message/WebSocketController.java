package com.unicalsocial.backend.message;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
@AllArgsConstructor
public class WebSocketController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendToUser("/queue/messages")
    public Message send(Message message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public Collection<Message> getMessages(@PathVariable Integer conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }
}