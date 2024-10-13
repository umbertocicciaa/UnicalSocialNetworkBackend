package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.CreatedMipiceResponse;
import com.unicalsocial.backend.dtos.MipiaceResponse;
import com.unicalsocial.backend.models.Mipiace;
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
