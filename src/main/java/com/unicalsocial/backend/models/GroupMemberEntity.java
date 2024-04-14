package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
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

    public Object getJoinedDatetime() {
        return joinedDatetime;
    }

    public void setJoinedDatetime(Object joinedDatetime) {
        this.joinedDatetime = joinedDatetime;
    }

    public Object getLeftDatetime() {
        return leftDatetime;
    }

    public void setLeftDatetime(Object leftDatetime) {
        this.leftDatetime = leftDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberEntity that = (GroupMemberEntity) o;
        return userId == that.userId && conversationId == that.conversationId && Objects.equals(joinedDatetime, that.joinedDatetime) && Objects.equals(leftDatetime, that.leftDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, conversationId, joinedDatetime, leftDatetime);
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public ConversationEntity getConversationByConversationId() {
        return conversationByConversationId;
    }

    public void setConversationByConversationId(ConversationEntity conversationByConversationId) {
        this.conversationByConversationId = conversationByConversationId;
    }
}
