package com.example.bookreservation.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;
    private String bookName;
    private String bookGenre;
    private String bookCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "authors-books",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "authorID"))
    private List<AuthorEntity> authors;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<ReservationEntity> reservations;

    public BookEntity() {
    }
}
