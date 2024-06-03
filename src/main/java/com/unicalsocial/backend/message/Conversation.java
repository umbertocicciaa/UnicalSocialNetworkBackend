package com.unicalsocial.backend.message;

import com.unicalsocial.backend.user.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Getter
@Setter
@Entity
@Hidden
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('conversation_conversation_id_seq'")
    @Column(name = "conversation_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "conversation_name", nullable = false, length = Integer.MAX_VALUE)
    private String conversationName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> participants;
}