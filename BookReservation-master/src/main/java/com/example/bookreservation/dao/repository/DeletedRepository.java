package com.example.bookreservation.dao.repository;

import com.example.bookreservation.dao.entity.DeletedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeletedRepository extends JpaRepository<DeletedEntity, Integer> {
    DeletedEntity findByBookCodeIgnoreCase(String bookCode);
    List<DeletedEntity> findByBookNameIgnoreCase(String bookName);
}
