package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    Slice<PostEntity> findAllByOrderByCreateDatetimeDesc(Pageable pageable);
    Long countByCreatedByUserid(@NotNull UserEntity createdByUserid);
}
