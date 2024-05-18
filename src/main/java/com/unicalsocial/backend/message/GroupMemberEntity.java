package com.unicalsocial.backend.message;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "group_member")
public class GroupMemberEntity {
    @EmbeddedId
    private GroupMemberIdEntity id;

    @MapsId("conversationId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("nextval('group_member_conversation_id_seq'")
    @JoinColumn(name = "conversation_id", nullable = false)
    private ConversationEntity conversationEntity;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "joined_datetime")
    private Instant joinedDatetime;

    @Column(name = "left_datetime")
    private Instant leftDatetime;

}