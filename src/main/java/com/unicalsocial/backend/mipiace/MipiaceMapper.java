package com.unicalsocial.backend.mipiace;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MipiaceMapper {
    MipiaceMapper INSTANCE = Mappers.getMapper(MipiaceMapper.class);
    MipiaceDTO MipiaceToMipiaceDto(Mipiace mipiace);
    Mipiace MipiaceDtoToMipiace(MipiaceDTO mipiaceDTO);
}
