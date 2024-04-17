package com.unicalsocial.backend.user;

import com.unicalsocial.backend.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user", schema = "public", catalog = "unical_social_network")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails, Principal {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
