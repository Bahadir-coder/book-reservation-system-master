package com.example.bookreservation.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorID;
    private String authorName;
    private String authorSurname;
    private String authorFinCode;

    @ManyToMany(mappedBy = "authors", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BookEntity> books;

    public AuthorEntity() {
    }
}
