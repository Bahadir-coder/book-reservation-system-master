package com.example.bookreservation.dao.repository;

import com.example.bookreservation.dao.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    Optional<AuthorEntity> findByAuthorFinCodeIgnoreCase(String finCode);
    AuthorEntity findByAuthorFinCode(String finCode);
}
