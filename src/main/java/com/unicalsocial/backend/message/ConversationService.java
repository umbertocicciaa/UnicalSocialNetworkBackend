package com.unicalsocial.backend.message;

public interface ConversationService {
    Integer findByParticipants(Integer participant1Id, Integer participant2Id);
}
