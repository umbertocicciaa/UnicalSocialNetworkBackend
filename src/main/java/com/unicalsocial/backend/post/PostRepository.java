package com.unicalsocial.backend.post;

import com.unicalsocial.backend.post_type.PostTypeEntity;
import com.unicalsocial.backend.user.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

@Hidden
public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    Slice<PostEntity> findAllByOrderByCreateDatetimeDesc(Pageable pageable);
    Slice<PostEntity> findAllByPostTypeEntityAndCreatedByUseridOrderByCreateDatetimeDesc(@NotNull PostTypeEntity postTypeEntity, @NotNull UserEntity createdByUserid, Pageable pageable);
    Slice<PostEntity> findAllByPostTypeEntityOrderByCreateDatetimeDesc(PostTypeEntity postTypeEntity, Pageable pageable);
    Long countByCreatedByUserid(@NotNull UserEntity createdByUserid);
}
