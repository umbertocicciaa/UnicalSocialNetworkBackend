package com.unicalsocial.backend.role;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper ISTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(RoleEntity role);

    RoleEntity roleDTOToRoleEntity(RoleDTO roleDTO);
}
