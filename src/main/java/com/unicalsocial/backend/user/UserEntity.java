package com.unicalsocial.backend.user;

import com.unicalsocial.backend.models.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.unicalsocial.backend.role.RoleEntity;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.EAGER;

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
    @Column(unique = true)
    private String email;
    private String password;
    private boolean accountLocked;
    private boolean enabled;

    @ManyToMany(fetch = EAGER)
    private List<RoleEntity> roles;
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
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return profileName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getName() {
        return email;
    }
}
