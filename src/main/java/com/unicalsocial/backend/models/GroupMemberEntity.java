package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "group_member", schema = "public", catalog = "unical_social_network")
@IdClass(GroupMemberEntityPK.class)
public class GroupMemberEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "conversation_id")
    private int conversationId;
    @Basic
    @Column(name = "joined_datetime")
    private Object joinedDatetime;
    @Basic
    @Column(name = "left_datetime")
    private Object leftDatetime;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", nullable = false)
    private ConversationEntity conversationByConversationId;
}
