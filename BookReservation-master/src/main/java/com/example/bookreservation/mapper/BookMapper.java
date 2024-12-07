package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.AuthorEntity;
import com.example.bookreservation.dao.entity.BookEntity;
import com.example.bookreservation.model.input.BookDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "authors", source = "authorFinCodes", qualifiedByName = "mapAuthorFinCodesToAuthors")
//    @Mapping(target = "authors", source = "authorNames", qualifiedByName = "mapAuthorNamesToAuthors")
//    @Mapping(target = "authors", source = "authorSurnames", qualifiedByName = "mapAuthorSurnamesToAuthors")
    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    @Mapping(target = "bookPrice", source = "bookPrice")
    @Mapping(target = "bookType", source = "bookType")
    BookEntity mapDtoToEntity(BookDtoInput bookDto);

    @Mapping(target = "authorNames", source = "authors", qualifiedByName = "mapAuthorsToAuthorName")
    @Mapping(target = "authorSurnames", source = "authors", qualifiedByName = "mapAuthorsToAuthorSurname")
    @Mapping(target = "bookName", source = "bookName")
    @Mapping(target = "bookGenre", source = "bookGenre")
    @Mapping(target = "bookCode", source = "bookCode")
    @Mapping(target = "bookPrice", source = "bookPrice")
    @Mapping(target = "bookAverageStar", source = "bookAverageStar")
    @Mapping(target = "bookType", source = "bookType")
    BookDtoOutput mapEntityToDto(BookEntity bookEntity);

    List<BookDtoOutput> mapEntityToDtos(List<BookEntity> bookEntity);

    @Named("mapAuthorFinCodesToAuthors")
    default List<AuthorEntity> mapAuthorFinCodesToAuthors(List<String> authorFinCodes){
        if(authorFinCodes == null){
            return null;
        }
        return authorFinCodes.stream().map((f)->{
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setAuthorFinCode(f);
            return authorEntity;
        }).collect(Collectors.toList());
    }

    @Named("mapAuthorNamesToAuthors")
    default List<AuthorEntity> mapAuthorNamesToAuthors(List<String> authorNames){
        if(authorNames == null){
            return null;
        }
        return authorNames.stream().map((n)->{
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setAuthorName(n);
            return authorEntity;
        }).collect(Collectors.toList());
    }

    @Named("mapAuthorSurnamesToAuthors")
    default List<AuthorEntity> mapAuthorSurnamesToAuthors(List<String> authorSurnames){
        if(authorSurnames == null){
            return null;
        }
        return authorSurnames.stream().map((s)->{
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setAuthorSurname(s);
            return authorEntity;
        }).collect(Collectors.toList());
    }

    @Named("mapAuthorsToAuthorName")
    default List<String> mapAuthorsToAuthorName(List<AuthorEntity> authors){
        if(authors == null){
            return null;
        }
        return authors.stream().
                map(AuthorEntity::getAuthorName).
                collect(Collectors.toList());
    }

    @Named("mapAuthorsToAuthorSurname")
    default List<String> mapAuthorsToAuthorSurname(List<AuthorEntity> authors){
        if(authors == null){
            return null;
        }
        return authors.stream().
                map(AuthorEntity::getAuthorSurname).
                collect(Collectors.toList());
    }
}
