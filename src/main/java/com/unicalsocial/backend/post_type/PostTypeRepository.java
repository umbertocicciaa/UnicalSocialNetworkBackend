package com.unicalsocial.backend.post_type;

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
