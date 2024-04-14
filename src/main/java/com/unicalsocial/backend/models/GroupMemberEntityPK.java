package com.unicalsocial.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberEntityPK implements Serializable {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "conversation_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conversationId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberEntityPK that = (GroupMemberEntityPK) o;
        return userId == that.userId && conversationId == that.conversationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, conversationId);
    }
}
