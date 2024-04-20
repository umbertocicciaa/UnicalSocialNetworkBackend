package com.unicalsocial.backend.user;

import com.unicalsocial.backend.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "user", schema = "public", catalog = "unical_social_network")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private LocalDateTime signupDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(unique = true)
    @Email
    private String email;
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
