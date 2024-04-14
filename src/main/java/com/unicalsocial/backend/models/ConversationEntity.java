package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
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

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationEntity that = (ConversationEntity) o;
        return conversationId == that.conversationId && Objects.equals(conversationName, that.conversationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, conversationName);
    }

    public Collection<GroupMemberEntity> getGroupMembersByConversationId() {
        return groupMembersByConversationId;
    }

    public void setGroupMembersByConversationId(Collection<GroupMemberEntity> groupMembersByConversationId) {
        this.groupMembersByConversationId = groupMembersByConversationId;
    }

    public Collection<MessageEntity> getMessagesByConversationId() {
        return messagesByConversationId;
    }

    public void setMessagesByConversationId(Collection<MessageEntity> messagesByConversationId) {
        this.messagesByConversationId = messagesByConversationId;
    }
}
