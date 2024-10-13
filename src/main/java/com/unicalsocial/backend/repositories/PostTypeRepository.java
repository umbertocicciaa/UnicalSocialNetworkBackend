package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.PostTypeEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Hidden
@Transactional
public interface PostTypeRepository extends JpaRepository<PostTypeEntity,Integer> {
    @Transactional(readOnly = true)
    Optional<PostTypeEntity> findByPostTypeName(String name);
}
