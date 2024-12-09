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

    @GetMapping("/get/All")
    public List<BookDtoOutput> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/find/by/Name")
    public BookDtoOutput findByName(@RequestParam String bookName) {
        return bookService.findByName(bookName);
    }

    @GetMapping("/find/by/Genre")
    public List<BookDtoOutput> findByGenre(@RequestParam String bookGenre) {
        return bookService.findByGenre(bookGenre);
    }

    @GetMapping("/find/by/Code")
    public BookDtoOutput findByCode(@RequestParam String bookCode) {
        return bookService.findByCode(bookCode);
    }

    @PostMapping("/save")
    public void saveBook(@RequestBody BookDtoInput bookDtoInput) {
        bookService.saveBook(bookDtoInput);
    }

    @DeleteMapping("/delete/by/Code")
    public void deleteByCode(@RequestParam String bookCode) {
        bookService.deleteByBookCode(bookCode);
    }

    @PostMapping("/save/Star/by/{bookCode}")
    public void saveStarByBookCode(@RequestParam Integer bookStar, @PathVariable String bookCode) {
        bookService.saveStarByBookCode(bookStar, bookCode);
    }

    @GetMapping("/get/average/bookStars")
    public Double getAverageBookStars(@RequestParam String bookCode) {
        return bookService.getAverageBookStars(bookCode);
    }

    @GetMapping("/get/greater/than/or/equal/by/{bookPrice}")
    public List<BookDtoOutput> getGreaterThanOrEqualByPrice(@PathVariable Double bookPrice) {
        return bookService.getGreaterThanOrEqualByPrice(bookPrice);
    }

    @GetMapping("/get/greater/than/or/equal/{bookAverageStar}")
    public List<BookDtoOutput> getGreaterThanOrEqualByAverageStar(@PathVariable Double bookAverageStar) {
        return bookService.getGreaterThanOrEqualByAverageStar(bookAverageStar);
    }

    @GetMapping("/sort/cheap/to/expensive")
    public List<BookDtoOutput> sortCheapToExpensive() {
        return bookService.sortCheapToExpensive();
    }

    @GetMapping("/sort/expensive/to/cheap")
    public List<BookDtoOutput> sortExpensiveToCheap() {
        return bookService.sortExpensiveToCheap();
    }

    @GetMapping("/sort/little/to/much")
    public List<BookDtoOutput> sortLittleToMuch() {
        return bookService.sortLittleToMuch();
    }

    @GetMapping("/sort/much/to/little")
    public List<BookDtoOutput> sortMuchToLittle() {
        return bookService.sortMuchToLittle();
    }

    @GetMapping("/get/equal/by/{bookType}")
    public List<BookDtoOutput> getEqualByBookType(@PathVariable String bookType) {
        return bookService.getEqualByBookType(bookType);
    }

    @GetMapping("/get/between/{bookMinPrice}/and/{bookMaxPrice}")
    public List<BookDtoOutput> getBetweenBookMinPriceAndBookMaxPrice(@PathVariable Double bookMinPrice, @PathVariable Double bookMaxPrice) {
        return bookService.getBetweenBookMinPriceAndBookMaxPrice(bookMinPrice, bookMaxPrice);
    }
}
