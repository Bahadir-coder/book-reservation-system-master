package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.UserEntity;
import com.example.bookreservation.model.input.UserDtoInput;
import com.example.bookreservation.model.output.UserDtoOutput;
import com.example.bookreservation.model.output.UserDtoOutputForUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userSurname", source = "userSurname")
    @Mapping(target = "userFinCode", source = "userFinCode")
    @Mapping(target = "userTel", source = "userTel")
    @Mapping(target = "userEmail", source = "userEmail")
    UserEntity mapDtoToEntity(UserDtoInput userDto);

    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userSurname", source = "userSurname")
    @Mapping(target = "userEmail", source = "userEmail")
    UserDtoOutput mapEntityToDto(UserEntity userEntity);

    @Mapping(target = "userID", source = "userID")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "userSurname", source = "userSurname")
    @Mapping(target = "userFinCode", source = "userFinCode")
    @Mapping(target = "userTel", source = "userTel")
    @Mapping(target = "userEmail", source = "userEmail")
    UserDtoOutputForUser mapEntityToDtoForUser(UserEntity userEntity);

    List<UserDtoOutput> mapEntityToDtos(List<UserEntity> userEntities);

    List<UserDtoOutputForUser> mapEntityToDtosForUser(List<UserEntity> userEntities);
}
