package com.unicalsocial.backend.comment;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


@Hidden
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    Slice<CommentEntity> findByPostEntityId(int postId, Pageable pageable);
}
