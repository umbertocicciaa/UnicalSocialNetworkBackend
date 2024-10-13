package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.PostEntity;
import com.unicalsocial.backend.models.PostMediaEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Hidden
@Transactional
public interface PostMediaRepository extends JpaRepository<PostMediaEntity,Integer> {
    @Transactional(readOnly = true)
    Optional<PostMediaEntity> findByPostEntity(@NotNull PostEntity postEntity);
}
