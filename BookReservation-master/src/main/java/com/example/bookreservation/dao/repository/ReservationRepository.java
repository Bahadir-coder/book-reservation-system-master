package com.example.bookreservation.dao.repository;

import com.example.bookreservation.dao.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer>, JpaSpecificationExecutor<ReservationEntity> {
    ReservationEntity findByReservationCodeIgnoreCase(String code);
    List<ReservationEntity> findByExpiryDateBefore(ZonedDateTime now);
}
