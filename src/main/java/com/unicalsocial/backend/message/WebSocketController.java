package com.unicalsocial.backend.message;

import com.unicalsocial.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
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
    public Message send(Message message, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        message.setSender(user);
        return messageService.saveMessage(message, Long.valueOf(user.getId()), Long.valueOf(message.getRecipient().getId()));
    }

    @GetMapping("/conversations/{conversationId}/messages")
    public Collection<Message> getMessages(@PathVariable Integer conversationId) {
        return messageService.getMessagesByConversationId(conversationId);
    }

}