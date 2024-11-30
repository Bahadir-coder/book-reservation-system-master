package com.example.bookreservation.controller;

import com.example.bookreservation.model.input.BookDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import com.example.bookreservation.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/all")
    public List<BookDtoOutput> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/find/by/{bookName}")
    public BookDtoOutput findByName(@PathVariable String bookName) {
        return bookService.findByName(bookName);
    }

    @GetMapping("/find/by/bookGenre")
    public List<BookDtoOutput> findByGenre(@RequestParam String bookGenre) {
        return bookService.findByGenre(bookGenre);
    }

    @GetMapping("/find/by/bookCode")
    public BookDtoOutput findByCode(@RequestParam String bookCode) {
        return bookService.findByCode(bookCode);
    }

    @PostMapping("/save")
    public void saveBook(@RequestBody BookDtoInput bookDtoInput) {
        bookService.saveBook(bookDtoInput);
    }

    @DeleteMapping("/delete/by/{bookCode}")
    public void deleteByBookCode(@PathVariable String bookCode){
        bookService.deleteByBookCode(bookCode);
    }
}
