package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.PostEntity;
import com.unicalsocial.backend.models.PostTypeEntity;
import com.unicalsocial.backend.models.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Hidden
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    Slice<PostEntity> findAllByOrderByCreateDatetimeDesc(Pageable pageable);

    Slice<PostEntity> findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(@NotNull PostTypeEntity postTypeEntity, @NotNull UserEntity createdByUserid, Pageable pageable);

    Slice<PostEntity> findAllByPostTypeEntityOrderByLikeDesc(PostTypeEntity postTypeEntity, Pageable pageable);

    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.postTypeEntity.id = :postTypeId " +
            "AND p.createdByUserid.id IN (" +
            "   SELECT f.id.followingUserId " +
            "   FROM FollowerEntity f " +
            "   WHERE f.id.followerUserId = :userId) " +
            "ORDER BY p.like DESC")
    Slice<PostEntity> findPostsByPostTypeAndFollowedUsers(
            @Param("postTypeId") Integer postTypeId,
            @Param("userId") Integer userId,
            Pageable pageable);

    Long countByCreatedByUserid(@NotNull UserEntity createdByUserid);
}
