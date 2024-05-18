package com.unicalsocial.backend.mipiace;

public interface MipiaceMapper {
    CreatedMipiceResponse toCreatedMipice(Mipiace mipiace);

    MipiaceResponse toMipiaceResponse(Mipiace mipiace);
}
