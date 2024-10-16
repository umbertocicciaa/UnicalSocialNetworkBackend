package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.FollowerEntity;
import com.unicalsocial.backend.models.FollowerId;
import com.unicalsocial.backend.models.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Hidden
public interface FollowerRepository extends JpaRepository<FollowerEntity, FollowerId> {

    @Query("SELECT COUNT(f) FROM FollowerEntity f WHERE f.id.followingUserId = :userId")
    Long countFollowersByUserId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(f) FROM FollowerEntity f WHERE f.id.followerUserId = :userId")
    Long countFollowingByUserId(@Param("userId") Integer userId);

    Slice<FollowerEntity> findFollowerEntitiesByFollowerUserEntity(UserEntity followerUserEntity,Pageable pageable);

}
