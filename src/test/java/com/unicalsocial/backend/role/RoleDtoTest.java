package com.unicalsocial.backend.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class RoleDtoTest {
    @Test
    public void shouldBeRoleDto(){
        RoleEntity roleEntity = RoleEntity.builder().name("admin").build();
        RoleDTO roleDTO = RoleMapper.ISTANCE.roleToRoleDTO(roleEntity);
        Assertions.assertEquals(roleDTO.getName(), roleEntity.getName());
    }

    @Test
    public void shouldBeRole(){
        RoleDTO roleDTO = RoleDTO.builder().name("admin").build();
        RoleEntity roleEntity = RoleMapper.ISTANCE.roleDTOToRoleEntity(roleDTO);
        Assertions.assertEquals(roleDTO.getName(), roleEntity.getName());
    }

}
