package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "post")
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
    @Version
    private Integer version;

}