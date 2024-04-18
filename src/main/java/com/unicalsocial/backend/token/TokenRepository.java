package com.unicalsocial.backend.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    Optional<TokenEntity> findByToken(String token);
}
