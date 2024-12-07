package com.example.bookreservation.service.specification;

import com.example.bookreservation.dao.entity.ReservationEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class ReservationSpecification {

    public static Specification<ReservationEntity> hasGreaterThanByCreatedDate(ZonedDateTime createdDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("createdDate"), createdDate);
    }

    public static Specification<ReservationEntity> hasGreaterThanByExpiryDate(ZonedDateTime expiryDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("expiryDate"), expiryDate);
    }
}
