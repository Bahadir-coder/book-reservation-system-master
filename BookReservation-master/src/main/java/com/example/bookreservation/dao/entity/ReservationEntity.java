package com.example.bookreservation.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationID;
    private String reservationType;
    private String reservationCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    @JsonBackReference
    private BookEntity book;

    @CreationTimestamp
    private ZonedDateTime createdDate;

    private ZonedDateTime expiryDate;

    public ReservationEntity() {
    }
}
