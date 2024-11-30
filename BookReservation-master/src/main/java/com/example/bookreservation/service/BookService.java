package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.AuthorEntity;
import com.example.bookreservation.dao.entity.BookEntity;
import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.repository.AuthorRepository;
import com.example.bookreservation.dao.repository.BookRepository;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.model.input.BookDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDtoOutput> getAllBooks() {
        System.out.println("Get All Books Started...");

        List<BookEntity> bookEntities = bookRepository.findAll();
        if (bookEntities.isEmpty()) {
            throw new NotFoundException("Books Not Found!");
        }
        List<BookDtoOutput> bookDtos = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtos;
    }

    public BookDtoOutput findByName(String name) {
        System.out.println("Find by Name Started...");

        BookEntity bookEntity = bookRepository.
                findByBookNameIgnoreCase(name);
        if (bookEntity == null) {
            throw new NotFoundException("Book Not Found!");
        }
        BookDtoOutput bookDtoOutput = bookMapper.
                mapEntityToDto(bookEntity);
        return bookDtoOutput;
    }

    public List<BookDtoOutput> findByGenre(String genre) {
        System.out.println("Find by Genre Started...");

        List<BookEntity> bookEntities = bookRepository.
                findByBookGenreIgnoreCase(genre);
        if (bookEntities.isEmpty()) {
            throw new NotFoundException("This genre Books Not Found!");
        }
        return bookMapper.mapEntityToDtos(bookEntities);
    }

    public BookDtoOutput findByCode(String code) {
        System.out.println("Find by Code Started...");
        BookEntity bookEntity = bookRepository.
                findByBookCode(code);
        if (bookEntity == null) {
            throw new NotFoundException("Book Not Found!");
        }
        BookDtoOutput bookDtoOutput = bookMapper.
                mapEntityToDto(bookEntity);
        return bookDtoOutput;
    }

    @Transactional
    public void saveBook(BookDtoInput bookDto) {
        System.out.println("Save Book Started...");

        List<BookEntity> books = bookRepository.findAll();
        for (int i = 0; i < books.size(); i++) {
            if (bookDto.getBookCode().equals(books.get(i).getBookCode())) {
                throw new FoundException("Book is also Found!");
            }
        }

        List<AuthorEntity> authors = bookDto.getAuthorFinCodes().stream()
                .map(authorFinCode -> {
                    AuthorEntity author = authorRepository.findByAuthorFinCode(authorFinCode);
                    if (author != null) {
                        return author;
                    } else {
                        AuthorEntity newAuthor = new AuthorEntity();
                        newAuthor.setAuthorFinCode(authorFinCode);
                        return authorRepository.save(newAuthor);
                    }
                }).collect(Collectors.toList());

        BookEntity bookEntity = bookMapper.mapDtoToEntity(bookDto);
        bookEntity.setAuthors(authors);
        bookRepository.save(bookEntity);
    }

    @Transactional
    public void deleteByBookCode(String bookCode){
        System.out.println("Delete by Book Code Started...");

        BookEntity bookEntity = bookRepository.
                findByBookCodeIgnoreCase(bookCode).
                orElseThrow(()-> new NotFoundException("Book Not Found!"));
        bookRepository.deleteById(bookEntity.getBookID());
    }

}