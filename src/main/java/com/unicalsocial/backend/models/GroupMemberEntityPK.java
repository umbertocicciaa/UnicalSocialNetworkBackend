package com.unicalsocial.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
public class GroupMemberEntityPK implements Serializable {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "conversation_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conversationId;
}
