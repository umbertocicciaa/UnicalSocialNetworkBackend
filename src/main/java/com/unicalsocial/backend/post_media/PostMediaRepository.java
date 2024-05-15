package com.unicalsocial.backend.post_media;

import com.unicalsocial.backend.post.PostEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface PostMediaRepository extends JpaRepository<PostMediaEntity,Integer> {
    Optional<PostMediaEntity> findByPostEntity(@NotNull PostEntity postEntity);
}
