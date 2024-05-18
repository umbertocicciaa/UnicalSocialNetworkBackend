package com.unicalsocial.backend.mipiace;

import com.unicalsocial.backend.exception.MipiaceNotFoundException;
import com.unicalsocial.backend.post.PostEntity;
import com.unicalsocial.backend.user.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Hidden
public class MipiaceServiceImpl implements MipiaceService {

    private final MipiaceRepository mipiaceRepository;

    @Override
    @Transactional
    public MipiaceDTO createMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var mipiace = this.mipiaceRepository.save(
                Mipiace.builder().id(MipiaceId.builder()
                        .postId(postId)
                        .userId(user.getId())
                        .build())
               .post(PostEntity.builder()
                                .id(postId)
                                .build())
               .user(user)
               .build());
        return MipiaceMapper.INSTANCE.MipiaceToMipiaceDto(mipiace);
    }

    @Override
    @Transactional(readOnly = true)
    public MipiaceDTO getMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).orElseThrow(MipiaceNotFoundException::new);
        return MipiaceMapper.INSTANCE.MipiaceToMipiaceDto(mipiace);
    }

    @Override
    @Transactional
    public Boolean deleteMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).orElseThrow(MipiaceNotFoundException::new);
        this.mipiaceRepository.delete(mipiace);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        return this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).isPresent();
    }
}
