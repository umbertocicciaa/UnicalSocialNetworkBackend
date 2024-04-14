package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "comment", schema = "public", catalog = "unical_social_network")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "created_by_userid")
    private int createdByUserid;
    @Basic
    @Column(name = "post_id")
    private int postId;
    @Basic
    @Column(name = "created_datetime")
    private Object createdDatetime;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "comment_replied_to_id")
    private Integer commentRepliedToId;
    @ManyToOne
    @JoinColumn(name = "created_by_userid", referencedColumnName = "id", nullable = false)
    private UserEntity userByCreatedByUserid;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private PostEntity postByPostId;
    @ManyToOne
    @JoinColumn(name = "comment_replied_to_id", referencedColumnName = "id")
    private CommentEntity commentByCommentRepliedToId;
    @OneToMany(mappedBy = "commentByCommentRepliedToId")
    private Collection<CommentEntity> commentsById;
}
