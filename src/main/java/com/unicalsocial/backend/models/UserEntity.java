package com.unicalsocial.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "user", schema = "public", catalog = "unical_social_network")
public class UserEntity {
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "profile_name")
    private String profileName;
    @Basic
    @Column(name = "signup_date")
    private Object signupDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @OneToMany(mappedBy = "userByCreatedByUserid")
    private Collection<CommentEntity> commentsById;
    @OneToMany(mappedBy = "userByFollowingUserId")
    private Collection<FollowerEntity> followersById;
    @OneToMany(mappedBy = "userByFollowerUserId")
    private Collection<FollowerEntity> followersById_0;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<GroupMemberEntity> groupMembersById;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<LikeEntity> likesById;
    @OneToMany(mappedBy = "userByCreatedByUserid")
    private Collection<PostEntity> postsById;
}
