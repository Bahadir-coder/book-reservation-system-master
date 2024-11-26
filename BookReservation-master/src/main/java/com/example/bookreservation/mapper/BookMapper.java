package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.BookEntity;
import com.example.bookreservation.model.input.BookDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author.authorFinCode", source = "authorFinCode")
    @Mapping(target = "author.authorName", source = "authorName")
    @Mapping(target = "author.authorSurname", source = "authorSurname")
    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    BookEntity mapDtoToEntity(BookDtoInput bookDto);

    @Mapping(target = "authorName", source = "author.authorName")
    @Mapping(target = "authorSurname", source = "author.authorSurname")
    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    BookDtoOutput mapEntityToDto(BookEntity bookEntity);

    List<BookDtoOutput> mapEntityToDtos(List<BookEntity> bookEntity);
}
