package com.unicalsocial.backend.mipiace;

import com.unicalsocial.backend.exception.MipiaceNotFoundException;
import com.unicalsocial.backend.exception.PostNotFoundException;
import com.unicalsocial.backend.exception.UserCantLikeTwoTimeSamePost;
import com.unicalsocial.backend.post.PostRepository;
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
    private final MipiaceMapper mipiaceMapper;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public CreatedMipiceResponse createMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var post = this.postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        var esisteMipiace = this.mipiaceRepository.findById(MipiaceId.builder()
                .postId(postId)
                .userId(user.getId())
                .build()).isPresent();
        if (esisteMipiace)
            throw new UserCantLikeTwoTimeSamePost();

        var mipiace = this.mipiaceRepository.save(
                Mipiace.builder()
                        .user(user)
                        .post(post)
                        .build()
        );
        return this.mipiaceMapper.toCreatedMipice(mipiace);
    }

    @Override
    @Transactional(readOnly = true)
    public MipiaceResponse getMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).orElseThrow(MipiaceNotFoundException::new);
        return this.mipiaceMapper.toMipiaceResponse(mipiace);
    }

    @Override
    @Transactional
    public EliminatoMipiaceResponse deleteMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).orElseThrow(MipiaceNotFoundException::new);
        this.mipiaceRepository.delete(mipiace);
        var present = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).isPresent();
        return EliminatoMipiaceResponse.builder()
                .deleted(present)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public EsisteMipiaceResponse existMipiace(int postId, Authentication authentication) {
        var user = (UserEntity) authentication.getPrincipal();
        return EsisteMipiaceResponse.builder()
                .esisteMiPiace(this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(user.getId()).build()).isPresent())
                .build();
    }
}
