package com.unicalsocial.backend.mipiace;

import org.springframework.stereotype.Service;

@Service
public class MipiaceMapperImpl implements MipiaceMapper {
    @Override
    public CreatedMipiceResponse toCreatedMipice(Mipiace mipiace) {
        return CreatedMipiceResponse.builder()
                .userId(mipiace.getUser().getId())
                .postId(mipiace.getPost().getId())
                .build();
    }

    @Override
    public MipiaceResponse toMipiaceResponse(Mipiace mipiace) {
        return MipiaceResponse.builder()
                .userId(mipiace.getUser().getId())
                .postId(mipiace.getPost().getId())
                .build();
    }
}
