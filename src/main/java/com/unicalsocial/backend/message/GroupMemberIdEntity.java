package com.unicalsocial.backend.message;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serial;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class GroupMemberIdEntity implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -7065078493425291452L;
    @NotNull
    @ColumnDefault("nextval('group_member_conversation_id_seq'")
    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;
}