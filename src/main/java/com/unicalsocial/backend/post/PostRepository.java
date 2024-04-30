package com.unicalsocial.backend.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    List<PostEntity> findAllByOrderByCreateDatetimeDesc();
}
