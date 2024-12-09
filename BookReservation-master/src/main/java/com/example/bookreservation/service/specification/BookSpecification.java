package com.example.bookreservation.service.specification;

import com.example.bookreservation.dao.entity.BookEntity;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookEntity> hasGreaterThanOrEqualByPrice(Double bookPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("bookPrice"), bookPrice);
    }

    public static Specification<BookEntity> hasGreaterThanOrEqualByAverageStar(Double bookAverageStar) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("bookAverageStar"), bookAverageStar);
    }

    public static Specification<BookEntity> hasEqualByBookType(String bookType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookType"), bookType);
    }

    public static Specification<BookEntity> hasBetweenBookMinPriceAndBookMaxPrice(Double bookMinPrice, Double bookMaxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("bookPrice"), bookMinPrice, bookMaxPrice);
    }
}