package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
