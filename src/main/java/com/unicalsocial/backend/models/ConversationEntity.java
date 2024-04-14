package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "conversation", schema = "public", catalog = "unical_social_network")
public class ConversationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "conversation_id")
    private int conversationId;
    @Basic
    @Column(name = "conversation_name")
    private String conversationName;
    @OneToMany(mappedBy = "conversationByConversationId")
    private Collection<GroupMemberEntity> groupMembersByConversationId;
    @OneToMany(mappedBy = "conversationByConversationId")
    private Collection<MessageEntity> messagesByConversationId;
}
