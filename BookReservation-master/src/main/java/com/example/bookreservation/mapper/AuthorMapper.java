package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.AuthorEntity;
import com.example.bookreservation.model.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "authorSurname", source = "authorSurname")
    @Mapping(target = "authorFinCode", source = "authorFinCode")
    AuthorEntity mapDtoToEntity(AuthorDto authorDto);

    @Mapping(target = "authorName", source = "authorName")
    @Mapping(target = "authorSurname", source = "authorSurname")
    @Mapping(target = "authorFinCode", source = "authorFinCode")
    AuthorDto mapEntityToDto(AuthorEntity authorEntity);

    List<AuthorDto> mapEntityToDtos(List<AuthorEntity> authorEntity);
}
