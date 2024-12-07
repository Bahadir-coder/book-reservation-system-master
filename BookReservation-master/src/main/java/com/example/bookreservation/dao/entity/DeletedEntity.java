package com.example.bookreservation.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deleted")
public class DeletedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;
    private String bookName;
    private String bookGenre;
    private String bookCode;
    private Double bookPrice;
    private Double bookAverageStar;

    public DeletedEntity() {
    }
}
