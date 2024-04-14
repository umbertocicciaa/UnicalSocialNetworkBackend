package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "message", schema = "public", catalog = "unical_social_network")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "sent_datetime")
    private Object sentDatetime;
    @Basic
    @Column(name = "conversation_id")
    private int conversationId;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", nullable = false)
    private ConversationEntity conversationByConversationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getSentDatetime() {
        return sentDatetime;
    }

    public void setSentDatetime(Object sentDatetime) {
        this.sentDatetime = sentDatetime;
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
        MessageEntity that = (MessageEntity) o;
        return id == that.id && conversationId == that.conversationId && Objects.equals(text, that.text) && Objects.equals(sentDatetime, that.sentDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, sentDatetime, conversationId);
    }

    public ConversationEntity getConversationByConversationId() {
        return conversationByConversationId;
    }

    public void setConversationByConversationId(ConversationEntity conversationByConversationId) {
        this.conversationByConversationId = conversationByConversationId;
    }
}
