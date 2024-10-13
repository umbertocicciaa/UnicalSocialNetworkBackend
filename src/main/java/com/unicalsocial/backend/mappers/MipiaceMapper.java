package com.unicalsocial.backend.mappers;

import com.unicalsocial.backend.dtos.CreatedMipiceResponse;
import com.unicalsocial.backend.dtos.MipiaceResponse;
import com.unicalsocial.backend.models.Mipiace;

public interface MipiaceMapper {
    CreatedMipiceResponse toCreatedMipice(Mipiace mipiace);

    MipiaceResponse toMipiaceResponse(Mipiace mipiace);
}
