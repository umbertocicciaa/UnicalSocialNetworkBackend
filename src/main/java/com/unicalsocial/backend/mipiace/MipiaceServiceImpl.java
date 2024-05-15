package com.unicalsocial.backend.mipiace;

import com.unicalsocial.backend.exception.MipiaceNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
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
    public MipiaceDTO createMipiace(int userId, int postId) {
        var mipiace = this.mipiaceRepository.save(Mipiace.builder().id(MipiaceId.builder().postId(postId).userId(userId).build()).build());
        return MipiaceMapper.INSTANCE.MipiaceToMipiaceDto(mipiace);
    }

    @Override
    @Transactional(readOnly = true)
    public MipiaceDTO getMipiace(int userid, int postId) {
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(userid).build()).orElseThrow(MipiaceNotFoundException::new);
        return MipiaceMapper.INSTANCE.MipiaceToMipiaceDto(mipiace);
    }

    @Override
    @Transactional
    public Boolean deleteMipiace(int userid, int postId) {
        var mipiace = this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(userid).build()).orElseThrow(MipiaceNotFoundException::new);
        this.mipiaceRepository.delete(mipiace);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean existMipiace(int userid, int postId) {
        return this.mipiaceRepository.findById(MipiaceId.builder().postId(postId).userId(userid).build()).isPresent();
    }
}
