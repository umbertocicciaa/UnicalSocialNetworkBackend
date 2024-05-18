package com.unicalsocial.backend.message;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "conversation")
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('conversation_conversation_id_seq'")
    @Column(name = "conversation_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "conversation_name", nullable = false, length = Integer.MAX_VALUE)
    private String conversationName;

}