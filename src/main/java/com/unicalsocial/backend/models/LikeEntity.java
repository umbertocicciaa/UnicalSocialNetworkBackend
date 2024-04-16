package com.unicalsocial.backend.models;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "like", schema = "public", catalog = "unical_social_network")
@IdClass(LikeEntityPK.class)
public class LikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private int postId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userByUserId;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private PostEntity postByPostId;
}
