package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "post_type", schema = "public", catalog = "unical_social_network")
public class PostTypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "post_type_name")
    private String postTypeName;
    @OneToMany(mappedBy = "postTypeByPostType")
    private Collection<PostEntity> postsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTypeEntity that = (PostTypeEntity) o;
        return id == that.id && Objects.equals(postTypeName, that.postTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postTypeName);
    }

    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }
}
