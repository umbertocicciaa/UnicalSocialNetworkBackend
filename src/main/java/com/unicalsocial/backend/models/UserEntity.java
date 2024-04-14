package com.unicalsocial.backend.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Object getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Object signupDate) {
        this.signupDate = signupDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(profileName, that.profileName) && Objects.equals(signupDate, that.signupDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, profileName, signupDate, id);
    }

    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    public Collection<FollowerEntity> getFollowersById() {
        return followersById;
    }

    public void setFollowersById(Collection<FollowerEntity> followersById) {
        this.followersById = followersById;
    }

    public Collection<FollowerEntity> getFollowersById_0() {
        return followersById_0;
    }

    public void setFollowersById_0(Collection<FollowerEntity> followersById_0) {
        this.followersById_0 = followersById_0;
    }

    public Collection<GroupMemberEntity> getGroupMembersById() {
        return groupMembersById;
    }

    public void setGroupMembersById(Collection<GroupMemberEntity> groupMembersById) {
        this.groupMembersById = groupMembersById;
    }

    public Collection<LikeEntity> getLikesById() {
        return likesById;
    }

    public void setLikesById(Collection<LikeEntity> likesById) {
        this.likesById = likesById;
    }

    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }
}
