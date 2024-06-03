package com.unicalsocial.backend.message;

public interface ConversationService {
    Integer findByParticipants(Long participant1Id, Long participant2Id);
}
