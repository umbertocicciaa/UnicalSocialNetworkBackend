package com.unicalsocial.backend.user;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

@Hidden
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Collection<UserEntity> findAllByOrderBySignupDateAsc();

    @Query(value = "SELECT u.*, COUNT(f.follower_user_id) AS follower_count " +
            "FROM \"user\" u "+
            "LEFT JOIN follower f ON u.id = f.follower_user_id " +
            "WHERE u.profile_name LIKE %:partialName% " +
            "GROUP BY u.id " +
            "ORDER BY follower_count DESC " +
            "LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    Slice<UserEntity> findAllByProfileNameContainingOrderedByFollowerCount(
            @Param("partialName") String partialName,
            @Param("pageSize") int pageSize,
            @Param("offset") int offset
    );

    @Query(value = "SELECT COUNT(DISTINCT u.id) " +
            "FROM \"user\" u "+
            "LEFT JOIN follower f ON u.id = f.follower_user_id " +
            "WHERE u.profile_name LIKE %:partialName%", nativeQuery = true)
    Long countUsersByProfileNameContaining(@Param("partialName") String partialName);

    Optional<UserEntity> findByProfileName(String username);
    Optional<UserEntity> findByEmail(String email);
}
