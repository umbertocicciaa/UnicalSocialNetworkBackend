package com.unicalsocial.backend.message;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/Conversation")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name="Conversation")
public class ConversationController {
    private final ConversationService conversationService;

    @GetMapping(value = "/conversations/{sender}/{reciver}")
    ResponseEntity<Integer> getConversations(@PathVariable("sender") int sender,@PathVariable("reciver") int reciver){
        return ResponseEntity.ok(this.conversationService.findByParticipants((long)sender,(long)reciver));
    }
}
