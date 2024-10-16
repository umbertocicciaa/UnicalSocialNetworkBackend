package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@OptimisticLocking(type=OptimisticLockType.VERSION)
@Table(name = "post")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('post_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_userid", nullable = false)
    private UserEntity createdByUserid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_type", nullable = false)
    private PostTypeEntity postTypeEntity;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_datetime")
    private Instant createDatetime;

    @NotNull
    @Column(name = "caption", nullable = false, length = Integer.MAX_VALUE)
    private String caption;

    @NotNull
    @Column(name = "\"like\"", nullable = false)
    private Integer like;

    @NotNull
    @Column(name = "version")
    @ColumnDefault("0")
    @Version
    private int version;

    @PrePersist
    protected void onCreate() {
        createDatetime = Instant.now();
    }
}