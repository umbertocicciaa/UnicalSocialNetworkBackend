package com.unicalsocial.backend.token;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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
