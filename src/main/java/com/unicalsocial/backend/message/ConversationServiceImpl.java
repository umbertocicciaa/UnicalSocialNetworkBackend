package com.unicalsocial.backend.message;

import com.unicalsocial.backend.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
@Hidden
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    @Override
    public Integer findByParticipants(Long participant1Id, Long participant2Id) {
        return this.conversationRepository.findOrCreateConversation(participant1Id,participant2Id,userRepository).getId();
    }
}
