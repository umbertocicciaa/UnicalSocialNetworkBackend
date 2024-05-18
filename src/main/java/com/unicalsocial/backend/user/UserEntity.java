package com.unicalsocial.backend.user;

import com.unicalsocial.backend.token.TokenEntity;
import jakarta.persistence.*;

import java.security.Principal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "\"user\"")
public class UserEntity implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Column(name = "first_name")
    private String firstname;
    @NotNull
    @Column(name = "last_name")
    private String lastname;
    @NotNull
    @Column(name = "profile_name", unique = true)
    private String profileName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Column(name = "signup_date")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant signupDate;
    @Column(name = "profile_picture", unique = true)
    private byte[] profilePicture;
    private String bio;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    @PrePersist
    protected void onCreate() {
        signupDate = Instant.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return profileName;
    }

}
