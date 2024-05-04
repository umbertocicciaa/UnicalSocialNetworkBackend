package com.unicalsocial.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Collection<UserEntity> findAllByOrderBySignupDateAsc();

    @Query(value = "SELECT u.*, COUNT(f.follower_user_id) AS follower_count " +
            "FROM \"user\" u "+
            "LEFT JOIN follower f ON u.id = f.follower_user_id " +
            "WHERE u.profile_name LIKE %:partialName% " +
            "GROUP BY u.id " +
            "ORDER BY follower_count DESC " +
            "LIMIT :pageSize OFFSET :offset", nativeQuery = true)
    Collection<UserEntity> findAllByProfileNameContainingOrderedByFollowerCount(
            @Param("partialName") String partialName,
            @Param("pageSize") int pageSize,
            @Param("offset") int offset
    );

    UserEntity findByProfileName(String username);
}
