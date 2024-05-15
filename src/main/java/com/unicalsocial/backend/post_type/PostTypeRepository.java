package com.unicalsocial.backend.post_type;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Hidden
public interface PostTypeRepository extends JpaRepository<PostTypeEntity,Integer> {
    Optional<PostTypeEntity> findByPostTypeName(String name);
}
