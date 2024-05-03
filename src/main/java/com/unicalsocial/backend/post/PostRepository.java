package com.unicalsocial.backend.post;

import com.unicalsocial.backend.user.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    Collection<PostEntity> findAllByOrderByCreateDatetimeDesc();
    Long countByCreatedByUserid(@NotNull UserEntity createdByUserid);
}
