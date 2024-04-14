package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedByUserid() {
        return createdByUserid;
    }

    public void setCreatedByUserid(int createdByUserid) {
        this.createdByUserid = createdByUserid;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Object getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Object createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentRepliedToId() {
        return commentRepliedToId;
    }

    public void setCommentRepliedToId(Integer commentRepliedToId) {
        this.commentRepliedToId = commentRepliedToId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id && createdByUserid == that.createdByUserid && postId == that.postId && Objects.equals(createdDatetime, that.createdDatetime) && Objects.equals(comment, that.comment) && Objects.equals(commentRepliedToId, that.commentRepliedToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdByUserid, postId, createdDatetime, comment, commentRepliedToId);
    }

    public UserEntity getUserByCreatedByUserid() {
        return userByCreatedByUserid;
    }

    public void setUserByCreatedByUserid(UserEntity userByCreatedByUserid) {
        this.userByCreatedByUserid = userByCreatedByUserid;
    }

    public PostEntity getPostByPostId() {
        return postByPostId;
    }

    public void setPostByPostId(PostEntity postByPostId) {
        this.postByPostId = postByPostId;
    }

    public CommentEntity getCommentByCommentRepliedToId() {
        return commentByCommentRepliedToId;
    }

    public void setCommentByCommentRepliedToId(CommentEntity commentByCommentRepliedToId) {
        this.commentByCommentRepliedToId = commentByCommentRepliedToId;
    }

    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }
}
