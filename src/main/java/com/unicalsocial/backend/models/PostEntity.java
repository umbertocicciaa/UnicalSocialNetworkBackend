package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "public", catalog = "unical_social_network")
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "created_by_userid")
    private int createdByUserid;
    @Basic
    @Column(name = "create_datetime")
    private Object createDatetime;
    @Basic
    @Column(name = "caption")
    private String caption;
    @Basic
    @Column(name = "post_type")
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

    public Object getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Object createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return id == that.id && createdByUserid == that.createdByUserid && postType == that.postType && Objects.equals(createDatetime, that.createDatetime) && Objects.equals(caption, that.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdByUserid, createDatetime, caption, postType);
    }

    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    public Collection<LikeEntity> getLikesById() {
        return likesById;
    }

    public void setLikesById(Collection<LikeEntity> likesById) {
        this.likesById = likesById;
    }

    public UserEntity getUserByCreatedByUserid() {
        return userByCreatedByUserid;
    }

    public void setUserByCreatedByUserid(UserEntity userByCreatedByUserid) {
        this.userByCreatedByUserid = userByCreatedByUserid;
    }

    public PostTypeEntity getPostTypeByPostType() {
        return postTypeByPostType;
    }

    public void setPostTypeByPostType(PostTypeEntity postTypeByPostType) {
        this.postTypeByPostType = postTypeByPostType;
    }
}
