package com.unicalsocial.backend.repositories;

import com.unicalsocial.backend.models.TokenEntity;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Hidden
@Transactional
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

  @Query(value = """
      select t from TokenEntity t inner join UserEntity u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  @Transactional(readOnly = true)
  List<TokenEntity> findAllValidTokenByUser(Integer id);

  @Transactional(readOnly = true)
  Optional<TokenEntity> findByToken(String token);
}
