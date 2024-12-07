package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.AuthorEntity;
import com.example.bookreservation.dao.entity.BookEntity;
import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.exception.StarException;
import com.example.bookreservation.dao.repository.AuthorRepository;
import com.example.bookreservation.dao.repository.BookRepository;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.model.input.BookDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import com.example.bookreservation.service.specification.BookSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public void deleteByBookCode(String bookCode) {
        System.out.println("Delete by Book Code Started...");

        BookEntity bookEntity = bookRepository.
                findByBookCodeIgnoreCase(bookCode).
                orElseThrow(() -> new NotFoundException("Book Not Found!"));
        bookRepository.deleteById(bookEntity.getBookID());
    }

    @Transactional
    public void saveStarByBookCode(Integer bookStar, String bookCode) {
        System.out.println("Save Star by Book Code Started...");
        Double sum = 0.0;
        BookEntity bookEntity = bookRepository.
                findByBookCode(bookCode);
        if (bookEntity == null) {
            throw new NotFoundException("Book Not Found!");
        }
        if (bookStar > 5) {
            throw new StarException("The star cannot be bigger than 5");
        }
        bookEntity.getBookStars().add(bookStar);
        for (int i = 0; i < bookEntity.getBookStars().size(); i++) {
            sum += bookEntity.getBookStars().get(i);
            Double average = sum / (bookEntity.getBookStars().size());
            bookEntity.setBookAverageStar(average);
        }
        bookRepository.save(bookEntity);
    }

    public Double getAverageBookStars(String bookCode) {
        System.out.println("Get Average Book Stars Started...");
        BookEntity bookEntity = bookRepository.
                findByBookCode(bookCode);
        if (bookEntity == null) {
            throw new NotFoundException("Book Not Found!");
        }
        Double average = bookEntity.getBookAverageStar();
        return average;
    }


    public List<BookDtoOutput> getGreaterThanOrEqualByPrice(Double bookPrice) {
        System.out.println("Get Greater Than or Equal by Price Started...");
        Specification<BookEntity> specification = Specification.where(null);
        if (bookPrice != null) {
            specification = specification.
                    and(BookSpecification.hasGreaterThanOrEqualByPrice(bookPrice));
        }
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookRepository.findAll(specification));
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> getGreaterThanOrEqualByAverageStar(Double bookAverageStar) {
        System.out.println("Get Greater Than or Equal by Average Star Started...");
        Specification<BookEntity> specification = Specification.where(null);
        if (bookAverageStar <= 5) {
            specification = specification.
                    and(BookSpecification.hasGreaterThanOrEqualByAverageStar(bookAverageStar));
        }
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookRepository.findAll(specification));
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> sortCheapToExpensive() {
        System.out.println("Sort Cheap to Expensive Started...");
        List<BookEntity> bookEntities = bookRepository.
                findAllBy(Sort.by("bookPrice").ascending());
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> sortExpensiveToCheap() {
        System.out.println("Sort Expensive to Cheap Started...");
        List<BookEntity> bookEntities = bookRepository.
                findAllBy(Sort.by("bookPrice").descending());
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> sortLittleToMuch() {
        System.out.println("Sort Little to Much Started...");
        List<BookEntity> bookEntities = bookRepository.
                findAllBy(Sort.by("bookAverageStar").ascending());
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> sortMuchToLittle() {
        System.out.println("Sort Much to Little Started...");
        List<BookEntity> bookEntities = bookRepository.
                findAllBy(Sort.by("bookAverageStar").descending());
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtoOutputs;
    }

    public List<BookDtoOutput> getEqualByBookType(String bookType){
        System.out.println("Get Equal by Book Type Started...");
        Specification<BookEntity> specification = Specification.where(null);
        if(bookType != null){
            specification = specification.and(BookSpecification.hasEqualByBookType(bookType));
        }
        List<BookEntity> bookEntities = bookRepository.
                findAll(specification);
        List<BookDtoOutput> bookDtoOutputs = bookMapper.
                mapEntityToDtos(bookEntities);
        return bookDtoOutputs;
    }
}
