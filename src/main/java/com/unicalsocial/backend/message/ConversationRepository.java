package com.unicalsocial.backend.message;

import com.unicalsocial.backend.user.UserEntity;
import com.unicalsocial.backend.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

@Hidden
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    @Query("SELECT c FROM Conversation c JOIN c.participants p1 JOIN c.participants p2 " +
            "WHERE p1.id = ?1 AND p2.id = ?2")
    Optional<Conversation> findByParticipants(Integer participant1Id, Integer participant2Id);

    default Conversation findOrCreateConversation(Integer senderId, Integer recipientId, UserRepository userRepository) {
        Optional<Conversation> optionalConversation = findByParticipants(senderId, recipientId);
        if (optionalConversation.isPresent()) {
            return optionalConversation.get();
        } else {
            Conversation conversation = new Conversation();
            conversation.setConversationName(senderId + "-" + recipientId);

            UserEntity sender = userRepository.findById(Math.toIntExact(senderId)).orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
            UserEntity recipient = userRepository.findById(Math.toIntExact(recipientId)).orElseThrow(() -> new IllegalArgumentException("Invalid recipient ID"));

            conversation.setParticipants(Set.of(sender, recipient));
            return save(conversation);
        }
    }
}