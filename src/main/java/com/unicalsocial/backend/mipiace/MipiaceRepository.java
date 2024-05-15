package com.unicalsocial.backend.mipiace;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
@Hidden
public interface MipiaceRepository extends JpaRepository<Mipiace,MipiaceId> {
}
