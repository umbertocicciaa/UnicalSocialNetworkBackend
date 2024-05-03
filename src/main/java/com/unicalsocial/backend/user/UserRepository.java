package com.unicalsocial.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Collection<UserEntity> findAllByOrderBySignupDateAsc();
    UserEntity findByProfileName(String username);
}
