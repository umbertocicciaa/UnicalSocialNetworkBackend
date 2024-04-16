package com.unicalsocial.backend.models;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;


@Entity
@Data
@Table(name = "post", schema = "public", catalog = "unical_social_network")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "created_by_userid",insertable = false,updatable = false)
    private int createdByUserid;
    @Basic
    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;
    @Basic
    @Column(name = "caption")
    private String caption;
    @Basic
    @Column(name = "post_type",insertable = false,updatable = false)
    private int postType;
    @OneToMany(mappedBy = "postByPostId")
    private Collection<CommentEntity> commentsById;
    @OneToMany(mappedBy = "postByPostId")
    private Collection<LikeEntity> likesById;
    @ManyToOne
    @JoinColumn(name = "created_by_userid", referencedColumnName = "id", nullable = false)
    private UserEntity userByCreatedByUserid;
    @ManyToOne
    @JoinColumn(name = "post_type", referencedColumnName = "id", nullable = false)
    private PostTypeEntity postTypeByPostType;

}
