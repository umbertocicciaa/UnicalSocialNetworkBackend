package com.unicalsocial.backend.mipiace;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Hidden
@Transactional
public interface MipiaceRepository extends JpaRepository<Mipiace,MipiaceId> {
    void deleteMipiaceByPostId(Integer post_id);
}
