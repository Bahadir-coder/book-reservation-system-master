package com.example.bookreservation.dao.repository;

import com.example.bookreservation.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserFinCodeIgnoreCase(String finCode);
}
