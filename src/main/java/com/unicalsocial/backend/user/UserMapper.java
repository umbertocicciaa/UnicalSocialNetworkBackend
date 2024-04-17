package com.unicalsocial.backend.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper ISTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source="firstName",target = "firstName")
    @Mapping(source="lastName",target = "lastName")
    @Mapping(source="profileName",target = "profileName")
    @Mapping(source="signupDate",target = "signupDate")
    UserDTO userToUserDto(UserEntity user);

    @Mapping(source="firstName",target = "firstName")
    @Mapping(source="lastName",target = "lastName")
    @Mapping(source="profileName",target = "profileName")
    @Mapping(source="signupDate",target = "signupDate")
    UserEntity userDtoToUser(UserDTO userDto);


}