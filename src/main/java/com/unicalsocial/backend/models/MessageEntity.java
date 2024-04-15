package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;


@Entity
@Data
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
    private LocalDateTime sentDatetime;
    @Basic
    @Column(name = "conversation_id",insertable = false,updatable = false)
    private int conversationId;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", nullable = false)
    private ConversationEntity conversationByConversationId;

}
