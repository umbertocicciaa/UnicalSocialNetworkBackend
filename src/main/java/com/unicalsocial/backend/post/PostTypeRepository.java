package com.unicalsocial.backend.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTypeRepository extends JpaRepository<PostTypeEntity,Integer> {
    PostTypeEntity findByPostTypeName(String name);
}
