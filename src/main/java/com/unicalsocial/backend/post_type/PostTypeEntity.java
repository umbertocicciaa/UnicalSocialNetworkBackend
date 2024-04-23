package com.unicalsocial.backend.post_type;

import com.unicalsocial.backend.post.PostEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
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
}
