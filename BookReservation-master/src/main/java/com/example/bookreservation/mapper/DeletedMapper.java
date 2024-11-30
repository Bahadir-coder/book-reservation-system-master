package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.DeletedEntity;
import com.example.bookreservation.model.DeletedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeletedMapper {
    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    DeletedEntity mapDtoToEntity(DeletedDto deletedDto);

    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    DeletedDto mapEntityToDto(DeletedEntity deletedEntity);

    List<DeletedDto> mapEntityToDtos(List<DeletedEntity> deletedEntities);
}
