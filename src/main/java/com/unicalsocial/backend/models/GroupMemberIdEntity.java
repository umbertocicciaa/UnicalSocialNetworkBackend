package com.unicalsocial.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class GroupMemberId implements java.io.Serializable {
    private static final long serialVersionUID = -7065078493425291452L;
    @NotNull
    @ColumnDefault("nextval('group_member_conversation_id_seq'")
    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;
}