package com.example.bookreservation.dao.repository;

import com.example.bookreservation.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findByBookNameIgnoreCase(String name);

    List<BookEntity> findByBookGenreIgnoreCase(String genre);

    Optional<BookEntity> findByBookCodeIgnoreCase(String code);

    BookEntity findByBookCode(String code);
}
