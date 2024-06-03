package com.unicalsocial.backend.message;
import com.unicalsocial.backend.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
@AllArgsConstructor
@Hidden
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Override
    public Message saveMessage(Message message) {
        message.setSentDatetime(Instant.now());
        var conversation = conversationRepository.findOrCreateConversation(message.getSender().getId(), message.getRecipient().getId(), userRepository);
        message.setConversation(conversation);
        return messageRepository.save(message);
    }

    @Override
    public Collection<Message> getMessagesByConversationId(Integer conversationId) {
        return messageRepository.findByConversationIdOrderBySentDatetimeDesc(conversationId);
    }

}
